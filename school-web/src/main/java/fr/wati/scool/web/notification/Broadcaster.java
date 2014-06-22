/**
 * 
 */
package fr.wati.scool.web.notification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.wati.scool.web.notification.PushEvent.PushEventType;

/**
 * @author Rachid Ouattara
 *
 */
public class Broadcaster implements Serializable {
    private static final long serialVersionUID = -368230891317363146L;
    
    static ExecutorService executorService =
        Executors.newSingleThreadExecutor();

    public interface BroadcastListener {
        void receiveBroadcast(PushEvent pushEvent);
    }
    
    private static Map<PushEventType, LinkedList<BroadcastListener>> listenersMap=new HashMap<>();
  
    
    public static synchronized void register(
            BroadcastListener listener,PushEventType eventType) {
    	if(listenersMap.get(eventType)==null){
    		listenersMap.put(eventType, new LinkedList<BroadcastListener>());
    	}
    	listenersMap.get(eventType).add(listener);
    }
    
    public static synchronized void unregister(
            BroadcastListener listener,PushEventType eventType) {
    	listenersMap.get(eventType).remove(listener);
    }
    
    public static synchronized void broadcast(
            final PushEvent pushEvent,final PushEventType eventType) {
        Set<BroadcastListener>  listenersToFire=new HashSet<>();
        if(listenersMap.get(PushEventType.ALL)==null){
        	listenersMap.put(PushEventType.ALL, new LinkedList<BroadcastListener>());
        }
        listenersToFire.addAll(listenersMap.get(PushEventType.ALL));
        if(listenersMap.get(eventType)==null){
        	listenersMap.put(eventType, new LinkedList<BroadcastListener>());
        }
        listenersToFire.addAll(listenersMap.get(eventType));
    	
        for (final BroadcastListener listener: listenersToFire)
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    listener.receiveBroadcast(pushEvent);
                }
            });
    }
}
