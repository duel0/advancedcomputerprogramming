const twitterClient = require('./init')
var counts = 0;
var tweet_array_IDs = [];
var hashtag_search = "covid";
var pattern = "vaccine";
var redis = require("redis"),
	redisClient;

redisClient = redis.createClient({
	legacyMode: true,
});

redisClient.connect();

/* dopo la connect al redis server faccio una get su patter (e.g., vaccine) per prendere
* l'ultimo valore scritto. Se la chiave non esiste (tweet_counts === null) setto counts = 0,
* altrimento converto ad int tramite parseInt l'oggetto tweet_counts 
* ALERT: se ho un crash del redis server la get non viene fatta più...devo riavviare server.js
*/
redisClient.get(pattern, function (err, tweet_counts){

	if (err !== null){
	
		//gestisci l'errore
		console.log("Error: " + err);
	}

	// 
	if (tweet_counts === null)
		counts = 0;
	else
		counts = parseInt(tweet_counts, 10);
	
});

var get_recent_tweet = function(hashtag_search, pattern){

	twitterClient.tweets.search({
	    q: '#' + hashtag_search,
	    result_type: 'recent', //get latest tweets with this hashtag
	}).then ((response) => {
		var tweet_array = response.statuses;
		tweet_array.forEach(function (tweet){			
			// if current tweet is not in the list, add it
			if (tweet_array_IDs.indexOf(tweet.id) === -1){
				tweet_array_IDs.push(tweet.id);
				if (tweet.text.indexOf(pattern) > -1){
                        		counts++;
								redisClient.incr(pattern);
                		}
			}
		});


	}).catch ((err) => console.error(err));
}


setInterval(function () {
	get_recent_tweet(hashtag_search, pattern);	
	module.exports = counts;
}, 3000);

