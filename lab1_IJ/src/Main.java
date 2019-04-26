import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<Integer> que = new Queue<Integer>();

        int i;
        boolean flag = false;

        // add test
        System.out.println("add 插入20个元素");
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
        System.out.println();
        System.out.println("remove 全部");


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
        System.out.println();
        System.out.println("offer 测试");


        for(i = 0; i < que.f_size * 3; ++i) {
            if (que.offer(i))
                System.out.println("offer " + i);
            else {
                System.out.println("offer 插入失败");
                break;
            }
        }

        // poll test
        System.out.println();
        System.out.println("poll 测试");

        flag = false;
        try {
            for(i = 0; i < que.f_size*2 + 1; ++i)
                System.out.println("poll " + que.poll());
        } catch (NoSuchElementException e) {
            System.out.println("poll NoSuchElementException");
            flag = true;
        }
        assert flag;

        flag = false;
        System.out.println();
        System.out.println("先插入10个出队5个");


        try{
            for(i = 0; i < 10; ++i) {
                que.add(i);
                System.out.println("add " + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("add 进入IllegalStateException 插入失败");
        }
        try {
            for(i = 0; i < 5; ++i)
                System.out.println("remove " + que.remove());
        } catch (NoSuchElementException e) {
            System.out.println("remove NoSuchElementException");
        }

        System.out.println();
        System.out.println("直到插满");

        try{
            for(i = 0; i < 20; ++i) {
                que.add(i);
                System.out.println("add " + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("add 进入IllegalStateException 插入失败");
        }


        flag = false;
        System.out.println();
        System.out.println("element 和 peek 测试");

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
