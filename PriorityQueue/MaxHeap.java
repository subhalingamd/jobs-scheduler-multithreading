package PriorityQueue;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
	private class Wrap<T extends Comparable> implements Comparable<Wrap<T>>{
		T el;
		int stamp;

		public Wrap(T el,int stamp){
			this.el=el;
			this.stamp=stamp;
		}

        @Override
        public int compareTo(Wrap<T> W){
            if (this.el.compareTo(W.el)!=0)
                return this.el.compareTo(W.el);
            if (this.stamp<W.stamp)
                return 1;
            else
                return -1;
        }
	}
	ArrayList<Wrap<T>> arr=new ArrayList();
	int ct=0; 
	int token=0;

    @Override
    public void insert(T element) {
    	arr.add(new Wrap<T>(element,token++));
    	int i=ct++;
    	Wrap<T> app=arr.get(i);
    	while (i>0 && app.compareTo(arr.get((int)((i-1)/2)))>0){
    		arr.set(i,arr.get((int)(i-1)/2));
    		i=(int)(i-1)/2;
    	}
    	arr.set(i,app);
    }

    @Override
    public T extractMax() {
    	if (arr.isEmpty())
        	return null;

        ct--;
        T ret=arr.get(0).el;
        Wrap<T> rem=arr.get(ct);
        //arr.set(0,rem);
        arr.remove(ct);

        if (ct==0)
        	return ret;
        
        int i=0;
        
    	while (2*i+1<ct){
    		int probed;
    		if (2*i+2>=arr.size()){ //ONE CHILD
                /* OLD >>
    			if (rem.el.compareTo(arr.get(2*i+1).el)==0){ //Not required
    				if (rem.stamp>arr.get(2*i+1).stamp){  
    					arr.set(i,arr.get(2*i+1));
    					probed=2*i+1;
    				}
    				else
    					break;
    			}
    			else if (rem.el.compareTo(arr.get(2*i+1).el)<0){
    					arr.set(i,arr.get(2*i+1));
    					probed=2*i+1;
    			}
    			else
    					break;
                
                NEW >> */
                if (rem.compareTo(arr.get(2*i+1))<0){
                    arr.set(i,arr.get(2*i+1));
                    probed=2*i+1;
                }
                else
                    break;
    		}
            /* OLD >>>
    		else if (arr.get(2*i+1).el.compareTo(arr.get(2*i+2).el)==0){			// EQUAL
    			if (arr.get(2*i+1).stamp<arr.get(2*i+2).stamp){   			// Left came first
    				if (rem.el.compareTo(arr.get(2*i+1).el)<=0){
    					arr.set(i,arr.get(2*i+1));
    					probed=2*i+1;
    				}
    				else
    					break;

    			}
    			else{	
    				if (rem.el.compareTo(arr.get(2*i+2).el)<=0){
    					arr.set(i,arr.get(2*i+2));
    					probed=2*i+2;
    				}
    				else
    					break;												// Right came first
    			}
    		}
    		else{
    			Wrap<T> maxChild;
    			if (arr.get(2*i+1).el.compareTo(arr.get(2*i+2).el)>0){
    				maxChild=arr.get(2*i+1);
    				probed=2*i+1;
    			}
    			else{
    				maxChild=arr.get(2*i+2);
    				probed=2*i+2;
    																// Not equal, get Max
    			}
    			if (rem.el.compareTo(maxChild.el)<=0){
    				arr.set(i,maxChild);
    			}
    			else
    				break;
    		}

            NEW >>>*/
            else{
                Wrap<T> maxChild;
                if (arr.get(2*i+1).compareTo(arr.get(2*i+2))>0){
                    maxChild=arr.get(2*i+1);
                    probed=2*i+1;
                }
                else{
                    maxChild=arr.get(2*i+2);
                    probed=2*i+2;
                                                                    // Not equal, get Max
                }
                if (rem.compareTo(maxChild)<0){
                    arr.set(i,maxChild);
                }
                else
                    break;
            }

    		i=probed;
    	}

    	arr.set(i,rem);


    	return ret;     
    }

    public boolean isEmpty(){
        return arr.isEmpty();
    }

}