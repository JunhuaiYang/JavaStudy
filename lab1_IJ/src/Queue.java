import java.util.Stack;
import java.util.NoSuchElementException;

public class Queue<E> extends Stack<E>{
    private static final long serialVersionUID = 1L;
    private  Stack<E> stk; // skt只能作为入队栈  父类只能作为出队栈
    public final int f_size = 10;

    public Queue( )
    {
        stk = new Stack<E>();
    }

    public boolean add(E e)
            throws IllegalStateException, ClassCastException,
            NullPointerException, IllegalArgumentException
    {
        // 插入空元素
        if(e == null)
            throw new NullPointerException();

        //判断入堆是否已满
        if(stk.size() != f_size )
        {
            //直接进入入队栈
            stk.push(e);
        }
        else
        {
            // 判断出队栈是否有元素
            if( this.isEmpty())
            {
                //没有则将入队栈中的所有元素出栈到出队栈
                while (!stk.isEmpty()) {
                    this.push(stk.pop());
                }
                //进入入队栈
                stk.push(e);
            }
            else {
                throw new IllegalStateException();
            }
        }
        return true;

    }
    public boolean offer(E e)
            throws ClassCastException, NullPointerException, IllegalArgumentException
    {
        // 插入空元素
        if(e == null)
            throw new NullPointerException();

        //判断入堆是否已满
        if(stk.size() != f_size )
        {
            //直接进入入队栈
            stk.push(e);
        }
        else
        {
            // 判断出队栈是否有元素
            if( this.isEmpty())
            {
                //没有则将入队栈中的所有元素出栈到出队栈
                while (!stk.isEmpty()) {
                    this.push(stk.pop());
                }
                //进入入队栈
                stk.push(e);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public E remove( ) throws NoSuchElementException
    {
        //先看出队栈是否有元素
        if( !this.isEmpty() )
        {
            // 直接出队
            return this.pop();
        }
        else
        {
            // 如果入队栈不为0，则将入队栈出到出队栈
            if( !stk.isEmpty() )
            {
                while (!stk.isEmpty()) {
                    this.push(stk.pop());
                }
                //再出栈
                return this.pop();
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }

    public E poll()
    {
        //先看出队栈是否有元素
        if( !this.isEmpty() )
        {
            // 直接出队
            return this.pop();
        }
        else
        {
            // 如果入队栈不为0，则将入队栈出到出队栈
            if( !stk.isEmpty() )
            {
                while (!stk.isEmpty()) {
                    this.push(stk.pop());
                }
                //再出栈
                return this.pop();
            }
            else {
                return null;
            }
        }
    }

    public E peek()
    {
        //先看出队栈是否有元素
        if( !this.isEmpty() )
        {
            // 直接出队
            return super.peek();
        }
        else
        {
            // 如果入队栈不为0，则将入队栈出到出队栈
            if( !stk.isEmpty() )
            {
//            	System.out.println(stk.empty());
                while (!stk.empty()) {
                    this.push(super.pop());
                }
                //再出栈
                return this.peek();
            }
            else {
                return null;
            }
        }
    }

    public E element( ) throws NoSuchElementException
    {
        //先看出队栈是否有元素
        if( !this.isEmpty() )
        {
            // 直接出队
            return super.peek();
        }
        else
        {
            // 如果入队栈不为0，则将入队栈出到出队栈
            if( !stk.isEmpty() )
            {
                while (!stk.isEmpty()) {
                    this.push(stk.pop());
                }
                //再出栈
                return this.peek();
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
}
