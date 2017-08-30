package hash;

/**
 * Home-made HashTable data structure.
 */
public class BasicHashTable<X, Y> {
	private HashEntry[] data;
	private int capacity;
	private int size;
	
	public BasicHashTable(int tableSize){
		this.capacity = tableSize;
		this.data = new HashEntry[this.capacity];
		this.size = 0;
	}
	
	public int size(){
		return this.size;
	}
	
	public Y get(X key){
		int hash = calculateHash(key);
		if(data[hash] == null){
			return null;
		}
		else{
			return (Y)data[hash].getValue();
		}
	}
	
	public void put(X key, Y value){
		int hash = calculateHash(key);
		data[hash] = new HashEntry<X, Y>(key, value);
		size++;
	}
	
	public void delete(X key){
		Y value = get(key);
		//clear hashtable slot for the key and return removed value
		if(value != null){
			int hash = calculateHash(key);
			data[hash] = null;
			size--;
			hash = (hash + 1) % this.capacity;
			
			//if the next slot isn't empty re-add it so we can keep the hash algorithms clean
			while (data[hash] != null){
				HashEntry hashEntry = data[hash];
				data[hash] = null;
				put((X)hashEntry.getKey(), (Y)hashEntry.getValue());
				size--;
				hash = (hash + 1) % this.capacity;
			}
		}
	}
	
	public boolean hasKey(X key){
		int hash = calculateHash(key);
		
		if(data[hash] == null){
			return false;
		}
		else{
			if(data[hash].getKey().equals(key)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasValue(Y value){
		//note that we loop through _capacity_
		for(int i = 0; i < this.capacity; i++){
			HashEntry he = data[i];
			if(he != null && he.getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	private int calculateHash(X key){
		int hash = (key.hashCode() % this.capacity);
		//deal with collisions
		while(data[hash] != null && !data[hash].getKey().equals(key)){
			hash = (hash + 1) % this.capacity;
		}
		return hash;
	}
	
	private class HashEntry<X, Y>{
		private X key;
		private Y value;
			
		
		public HashEntry(X key, Y value) {
			this.key = key;
			this.value = value;
		}
		
		public X getKey() {
			return key;
		}
		
		public void setKey(X key) {
			this.key = key;
		}
		
		public Y getValue() {
			return value;
		}
		
		public void setValue(Y value) {
			this.value = value;
		}		
	}
}
