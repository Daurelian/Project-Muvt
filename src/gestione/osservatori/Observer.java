package gestione.osservatori;

import java.time.LocalDateTime;

public interface Observer {
    public boolean notifyMe(LocalDateTime message);
}
