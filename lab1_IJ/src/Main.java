import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<Integer> que = new Queue<>();
        que.f_size = 10;  // 设置大小

        int i;
        // add test
        System.out.println("add 插入30个元素");
        try{
            System.out.println("add: ");
            for(i = 0; i < que.f_size * 3; ++i) {
                que.add(i);
                System.out.printf("%d ", i);
            }
            System.out.println();
        } catch (IllegalStateException e) {
            System.out.println("\nadd 进入IllegalStateException 插入失败");
        }


        // remove test
        System.out.println();
        System.out.println("remove 全部");

        try {
            System.out.println("remove： ");
            for(i = 0; i < que.f_size*3; ++i)
                System.out.printf("%d ", que.remove());
            System.out.println();
        } catch (NoSuchElementException e) {
            System.out.println("\nremove NoSuchElementException");
        }

        // offer test
        System.out.println();
        System.out.println("offer 测试");

        System.out.println("offer: ");
        for(i = 0; i < que.f_size * 3; ++i) {
            if (que.offer(i))
                System.out.printf("%d ", i);
            else {
                System.out.println("\noffer 插入失败");
                break;
            }
        }

        // poll test
        System.out.println();
        System.out.println("poll 测试");

        try {
            System.out.println("poll: ");
            for(i = 0; i < que.f_size*2 + 1; ++i)
                System.out.printf("%d ", que.poll());
            System.out.println();
        } catch (NoSuchElementException e) {
            System.out.println("\npoll NoSuchElementException");
        }

        System.out.println();
        System.out.println("先插入10个出队5个");

        try{
            System.out.println("add: ");
            for(i = 0; i < 10; ++i) {
                que.add(i);
                System.out.printf("%d ", i);
            }
        } catch (IllegalStateException e) {
            System.out.println("\nadd 进入IllegalStateException 插入失败");
        }
        try {
            System.out.println("remove: ");
            for(i = 0; i < 5; ++i)
                System.out.printf("%d ", que.remove());
        } catch (NoSuchElementException e) {
            System.out.println("\nremove NoSuchElementException");
        }

        System.out.println();
        System.out.println("直到插满");

        try{
            System.out.println("add: ");
            for(i = 0; i < 20; ++i) {
                que.add(i);
                System.out.printf("%d ", i);
            }
        } catch (IllegalStateException e) {
            System.out.println("\nadd 进入IllegalStateException 插入失败");
        }

        System.out.println();
        System.out.println("element 和 peek 测试");

        try{
            for(i = 0; i < que.f_size * 2 + 1; ++i) {
                System.out.println("element " + que.element());
                System.out.println("peek " + que.peek());
                que.remove();
            }
        } catch (NoSuchElementException e) {
            System.out.println("element NoSuchElementException");
        }
    }
}
