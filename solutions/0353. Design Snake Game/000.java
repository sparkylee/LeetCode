class SnakeGame {
    int width, height;
    int [] [] food;
    int fi = 0;
    List<int []> snake;
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;      
        this.snake = new ArrayList<>();
        this.snake.add(new int[] {0,0});
    }
    
    public int move(String direction) {
        int [] posNew = nextPos(this.snake.getLast(), direction);
        if(posNew[0]<0 || posNew[0]>=this.height || posNew[1]<0 || posNew[1]>=this.width)
            return -1;
        if(fi<food.length && posNew[0]==food[fi][0] && posNew[1]==food[fi][1] ) {
            this.snake.add(posNew);
            fi++;
        } else {
            for(int i=0;i<this.snake.size() - 1;i++) {
                int [] pos_i = this.snake.get(i);
                int [] pos_iplus = this.snake.get(i+1);
                pos_i[0] = pos_iplus[0];
                pos_i[1] = pos_iplus[1];
                if(i<(this.snake.size() - 2) &&  pos_i[0]==posNew[0] && pos_i[1] == posNew[1])
                    return -1;                
            }
            int [] pos_new_head = this.snake.getLast();
            pos_new_head[0] = posNew[0];
            pos_new_head[1] = posNew[1];
        }
        return this.snake.size() - 1;
    }
    int [] nextPos(int [] pos, String dir) {
        int [] posNew = pos.clone();
        switch (dir.charAt(0)) {
            case 'U':
                posNew[0]--;
                break;
            case 'D':
                posNew[0]++;
                break;
            case 'L':
                posNew[1]--;
                break;    
            case 'R':
                posNew[1]++;
                break;
            default:
                break;
        }
        return posNew;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */