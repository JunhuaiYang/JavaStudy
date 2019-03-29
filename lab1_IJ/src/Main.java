import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> que = new Queue<Integer>();

        int i;
        boolean flag = false;

        // add test
        System.out.println("add");
        flag = false;
        try{
            for(i = 0; i < que.f_size * 3; ++i) {
                que.add(i);
                System.out.println("add " + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("add 进入IllegalStateException 插入失败");
            flag = true;
        }
        assert flag;

        // remove test
        System.out.println("remove");
        flag = false;
        try {
            for(i = 0; i < que.f_size*3; ++i)
            System.out.println("remove " + que.remove());
        } catch (NoSuchElementException e) {
            System.out.println("remove NoSuchElementException");
            flag = true;
        }
        assert flag;

        // offer test
        System.out.println("offer");
        for(i = 0; i < que.f_size * 3; ++i) {
            if (que.offer(i))
                System.out.println("offer " + i);
            else {
                System.out.println("offer 插入失败");
                break;
            }
        }

        // poll test
        flag = false;
        try {
            for(i = 0; i < que.f_size*2 + 1; ++i)
                System.out.println("poll " + que.poll());
        } catch (NoSuchElementException e) {
            System.out.println("poll NoSuchElementException");
            flag = true;
        }
        assert flag;


        // element test
        flag = false;
        System.out.println("element");
        try{
            for(i = 0; i < que.f_size * 2; ++i) {
                que.add(i);
                System.out.println("add " + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("add 进入IllegalStateException 插入失败");
            flag = true;
        }
        assert flag;

        flag = false;
        try{
            for(i = 0; i < que.f_size * 2 + 1; ++i) {
                System.out.println("element " + que.element());
                System.out.println("peek " + que.peek());
                que.remove();
            }
        } catch (NoSuchElementException e) {
            System.out.println("element NoSuchElementException");
            flag = true;
        }
        assert flag;
    }
}
