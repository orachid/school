/**
 * 
 */
package fr.wati.scool.web.addons;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.core.userdetails.User;

/**
 * @author Rachid Ouattara
 *
 */
public class Broadcaster implements Serializable {
    private static final long serialVersionUID = -368230891317363146L;
    
    static ExecutorService executorService =
        Executors.newSingleThreadExecutor();

    public interface BroadcastListener {
        void receiveBroadcast(User user);
    }
    
    private static LinkedList<BroadcastListener> listeners =
        new LinkedList<BroadcastListener>();
    
    public static synchronized void register(
            BroadcastListener listener) {
        listeners.add(listener);
    }
    
    public static synchronized void unregister(
            BroadcastListener listener) {
        listeners.remove(listener);
    }
    
    public static synchronized void broadcast(
            final User user) {
        for (final BroadcastListener listener: listeners)
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    listener.receiveBroadcast(user);
                }
            });
    }
}
