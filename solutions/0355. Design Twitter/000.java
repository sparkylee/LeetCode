class Twitter {
    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<int []>> tweetMap;
    int timestamp = 0 ;
    public Twitter() {
        this.followMap = new HashMap<>();
        this.tweetMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        List<int []> tweetList = this.tweetMap.getOrDefault(userId, new ArrayList<>());
        tweetList.add(new int[] {this.timestamp, tweetId});
        this.timestamp ++;
        this.tweetMap.putIfAbsent(userId, tweetList);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> indices = new HashMap<>();
        List<Integer> candidates = new ArrayList<>(this.followMap.getOrDefault(userId, new HashSet<>()));
        candidates.add(userId);
        for(int candidate: candidates) {
            indices.put(candidate, this.tweetMap.getOrDefault(candidate, new ArrayList<>()).size()-1);
        }
        int counter = 0;
        while(counter<10) {
            int tweet_most_recent_candidate = -1, tweet_most_recent_index=0, tweet_most_recent_timestamp=0, tweet_most_recent_id=0;
            for(int candidate: candidates) {
                List<int []> tweets =  this.tweetMap.getOrDefault(candidate, new ArrayList<>());
                int index = indices.get(candidate);
                if(index>=tweets.size() || index <0)
                    continue;
                int [] tweet = tweets.get(index);
                if(tweet_most_recent_candidate==-1 || tweet[0]>tweet_most_recent_timestamp) {
                    tweet_most_recent_candidate = candidate;
                    tweet_most_recent_timestamp = tweet[0];
                    tweet_most_recent_index = index;
                    tweet_most_recent_id = tweet[1];
                }
            }
            if(tweet_most_recent_candidate==-1)
                break;
            results.add(tweet_most_recent_id);
            indices.put(tweet_most_recent_candidate, tweet_most_recent_index-1);
            counter++;
        }
        return results;
    }
    
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees = this.followMap.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        this.followMap.putIfAbsent(followerId, followees);
        
    }
    
    public void unfollow(int followerId, int followeeId) {
      this.followMap.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */