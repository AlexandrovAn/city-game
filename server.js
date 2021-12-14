const express = require('express'); //requires express module
const socket = require('socket.io'); //requires socket.io module
const fs = require('fs');
const app = express();
var PORT = process.env.PORT || 3000;
const server = app.listen(PORT); //hosts server on localhost:3000



app.use(express.static('public'));
console.log('Server is running');
const io = socket(server);

var count = 1;
var word = "";
var firstClient = "";
var secondClient = "";


//Socket.io Connection------------------
io.on('connection', (socket) => {

    if (firstClient == "") {
        firstClient = socket.id
        console.log("Firts client has id: " + socket.id)
        io.emit('clientId', firstClient)
    } else {
        secondClient = socket.id
        console.log("Second client has id: " + socket.id)
        io.emit('clientId', secondClient)
    }

    io.emit('counter', count)

    socket.on('counter', () => {
        count++;
        console.log("Ход" + count)
        io.emit('counter', count)
    })

    socket.on('city', (city) => {
        word = city
        console.log("Слово от игрока " + socket.id + " - " + word)
        io.emit('city', word)
    })

    socket.on('surrender', (loser) => {
        console.log("Client with id: " + socket.id + " lose the game")
        io.emit('endGame', loser)
    })
})