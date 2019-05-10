import org.junit.Test;

public class addTwoDigits {
    @Test
    public void test1()
    {
        test(29);
    }

    private  void test(int n)
    {
        int result  = addTwoDigits(n);
        System.out.println(result);
    }
    int addTwoDigits(int n) {
      int sum = 0;
      while(n>0)
      {
          sum+=n%10;
          n = n/10;
      }
      return sum;
    }
}
