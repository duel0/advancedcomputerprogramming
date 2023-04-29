//init.js
//require('dotenv').config() <= se volessimo esportare come envar le credenziali di accesso
var credentials = require("./credentials.json");

const {TwitterClient} = require('twitter-api-client')

const twitterClient = new TwitterClient(credentials);
/*
const twitterClient = new TwitterClient({
    apiKey: 'API_KEY',
    apiSecret: 'API_SECRET',
    accessToken: 'ACCESS_TOKEN',
    accessTokenSecret: 'ACCESS_TOKEN_SECRET'
})
*/
module.exports = twitterClient
