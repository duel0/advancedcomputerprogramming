const twitterClient = require('./init')
var counts = 0;
var tweet_array_IDs = [];
var hashtag_search;

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
					console.log(counts);
                		}
			}
		});


	}).catch ((err) => console.error(err));
}


setInterval(function () {
	hashtag_search = "covid";
	get_recent_tweet(hashtag_search, "is");	
	module.exports = counts;
}, 3000);
;