//https://gist.github.com/nandor/9518116
//https://stackoverflow.com/questions/31756924/binary-search-tree-java?rq=1
public class BinaryNode<T extends Comparable<T>>
{
    private T             data;
    private int           height;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T data)
    {
        this(data, null, null);
    }
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    public void setData(T data)
    {
        this.data = data;
    } 
    
    public T getData()
    {
        return data;
    }
    //
    public BinaryNode<T> getLeft()
    {
        return left;
    }
    public BinaryNode<T> getRight()
    {
        return right;
    }
    //
    public void setLeft(BinaryNode<T> left)
    {
        this.left = left;
    }
    public void setRight(BinaryNode<T> right)
    {
        this.right = right;
    }
    //
    public int getHeight()
    {
        return height;
    }
    //
    public void setHeight(int height)
    {
        this.height = height;
    }
}