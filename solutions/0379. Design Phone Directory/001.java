class PhoneDirectory {
    Set<Integer> phone_in_use;
    Set<Integer> phone_avail;
    public PhoneDirectory(int maxNumbers) {
        this.phone_avail = new HashSet<>();
        this.phone_in_use = new HashSet<>();
        for(int i=0;i<maxNumbers;i++) {
            this.phone_avail.add(i);
        }
    }
    
    public int get() {
        if(this.phone_avail.isEmpty()) {
            return -1;
        }
        int phone = this.phone_avail.iterator().next();
        this.phone_in_use.add(phone);
        this.phone_avail.remove(phone);
        return phone;
    }
    
    public boolean check(int number) {
        return this.phone_avail.contains(number);
    }
    
    public void release(int number) {
        if(!this.phone_in_use.contains(number))
            return ;
        this.phone_in_use.remove(number);
        this.phone_avail.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */