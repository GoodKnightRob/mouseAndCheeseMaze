import java.util.*;
//Basic stack class backed by standard java arrays. Provides push(),
//pop(), top() methods along with list versions of the elements.
//When a push() would exceed the internal capacity of the internal
//array, the array is doubled in size.
//
//CONSTRAINT: Unless otherwise specified in individual methods, this
//class must implemented without relying on any other classes aside
//from standard arrays and Object.
public class AStack<T>{
  protected int size=0;
  protected int capacity;
  protected T [] stack;
  // protected <T> stack= new <T>[];
  
// Create a stack with the default capacity 10. The stack expands
// its internal array when the number of elements pushed() into it
// would exceed the internal capacity.
 @SuppressWarnings("unchecked")
  public AStack(){
    capacity=10;
    stack = (T[]) new Object[this.capacity];
    size=0;
  }
  
// Create a stack with the indicated initial capacity.
 @SuppressWarnings("unchecked")
  public AStack(int n){
    capacity=n;
    stack = (T[]) new Object[this.capacity];
  }
  
// Get the virtual size of the stack which is how many elements have
// been pushed into it but not popped.
  public int size(){
    return size;
  }
  
// Get the size of the internal array used by the stack to store
// elemnts. This number indicates how many elements can be stored in
// the stack before an expansion must occur.
  public int getCapacity(){
    return capacity;
  }
  
// Add a new element to the top of the stack. Expand the internal
// array if needed.
  public void push(T x){
    if (this.size==capacity){
      capacity+=capacity;
      //stack.add(x);
      T [] temp = (T[]) new Object[this.capacity];// Create a temporary array
      for(int i=0; i<size;i++){// itterate and save for the new size
        temp[i]=stack[i];
      }
      stack=temp;// have the current stack reference to the temporary array
    }
//will add an object to the top of array 
    for(int i=0; i<stack.length; i++){
      if(i == size){
        stack[i]=x;
      }
    }
    size++;
  }
  
// Remove the top element of the stack and return it. If the stack
// is emmpty, throw a RuntimeException with the message
// "Stack empty"
  public T pop(){
    
    if(this.size>0){
      // T top =(T)stack.get(stack.size()-1);
      // stack.remove(stack.size()-1);
      //size--;
      //return top;
      T [] temp = (T[]) new Object[this.capacity];// Create a temporary array
      T top = stack[size-1];// save the top element of the stack to variable T
      for(int i=0; i<size-1; i++){// itterate through the stack 
        temp[i]=stack[i];// fill in the temporary stack with every value except the top
      }
      
      stack=temp;// have the new stack reference the temp
      size--;//reduce size
      return top;//return the previous top value
    }
    else
      throw new RuntimeException("Stack empty");
  }
  
// Return the top element of the stack .If the stack is emmpty,
// throw a RuntimeException with the message "Stack empty"
  public T top(){
    if(size>0)
      return stack[size-1];
    else
      throw new RuntimeException("Stack empty");
  }
  
// Pretty print the stack as a string.  The string should reflect
// the size() of the stack and not include elements of the internal
// array which are not defined.  While building the string
// representation you may use the String, StringBuilder, and
// StringBuffer classes.
  public String toString(){
   String string = "[";
    for(int i = 0; i <size; i++)
    {
      string += stack[i].toString();
      if(i != size - 1)
        string += ", ";
    }
    string += "]";
    return string;
  }
  
// Return a list of the elements in the stack. The bottom stack item
// is at index 0 and the top stack item is at index size()-1. This
// method does not change the stack. For this method only you may
// use ArrayLists.
  public List<T> toList(){
   ArrayList stack2 = new ArrayList<T>();
    T list2;
    for(int i=0; i<size; i++){
      list2 = stack[i];
      stack2.add(list2);
    }
    return (List<T>) stack2;
  }
  
}