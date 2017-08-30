package queue;

/**
 * Home-made Queue data structure.
 * Queue is FIFO.
 * Queue's Big O:
 * enQueue = O(1)
 * deQueue = O(1)
 * search = O(n)
 * access = O(n)
 */
public class BasicQueue<X> {
	private X[] data;
	private int front;
	private int end;

	public BasicQueue() {
		this(1000);
	}
	
	public BasicQueue(int size) {
		this.front = -1;
		this.end = -1;
		data = (X[])new Object[size];
	}
	
	public int size(){
		if(front == -1 && end == -1){
			return 0;
		}
		else{
			return end - front + 1;
		}
	}
	
	public void enQueue(X item){
		//queue is full?
		if((end + 1) % data.length == front){
			throw new IllegalStateException("Queue is full, can't enqueue.");
		}
		//first add?
		else if(size() == 0){
			front++;
			end++;
			data[end] = item;
		}
		end++;
		data[end] = item;
	}
	
	public X deQueue(){
		X item = null;
		//is it empty?
		if(size() == 0){
			throw new IllegalStateException("Queue is empty, can't dequeue.");
		}
		//is it last item?
		else if(front == end){
			item = data[front];
			data[front] = null;
			front = -1;
			end = -1;
		}
		else{
			item = data[front];
			data[front] = null;
			front++;
		}
		return item;
	}
	
	public boolean contains(X item){
		boolean found = false;
		
		if(size() == 0){
			return found;
		}
		
		for(int i = front; i < end; i++){
			if(item.equals(data[i])){
				found = true;
			}
		}
		return found;
	}
	
	public X access(int position){
		if(size() == 0 || position > size()){
			throw new IllegalArgumentException("Queue is empty or position is greater than the size of queue.");
		}
		
		//since the front moves for performance reasons and will not be always 0, we must track the position we want to return
		int index = 0;
		for(int i = front; i < end; i++){
			if(index == position){
				return data[i];
			}
			index++;
		}
		
		//nothing found
		return null;
	}
	
}
