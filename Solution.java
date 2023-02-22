class Solution {
    public int shipWithinDays(int[] weights, int days) {
        /* 
        By default, we know the smallest possible range of capacities has to be greater than or equal to
        the max weight. this gives us the left side of our binary search.
        The max has to be less than the sum. this gives us the right side of our binary search.,
        */
        int l = 0; 
        int r = 0;
        for(int i : weights){
            l = Math.max(l, i);
            r += i;
        }
        int res = r;
        //init binary search on all capacities between max of weights and sum of weights.
        while(l <= r){
            int capacity = (l+r)/2;
            if(canShip(capacity, weights, days)){
                res = Math.min(res, capacity);
                r = capacity-1;
            }
            else
            {
                l = capacity+1;
            }
        }
        return res;

    }
    //helper function to determine if a given capacity can ship in x # of days.
    public boolean canShip(int cap, int[] weights, int days){
        int ships = 1; // init default # of ships.
        int currCap = cap;
        for(int w : weights){
            if(currCap-w < 0){
                ships++;
                currCap = cap;
            }
            currCap -= w;
        }
        return ships <= days;
    }
}
