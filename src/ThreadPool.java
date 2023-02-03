import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        // Créer un pool de threads dynamiques avec Executors.newCachedThreadPool
        ExecutorService dynamicThreadPool = Executors.newCachedThreadPool();

        // Soumettre des tâches à exécuter avec le pool de threads
        dynamicThreadPool.execute(() -> {
            System.out.println("Tâche 1 en cours d'exécution");
        });
        dynamicThreadPool.execute(() -> {
            System.out.println("Tâche 2 en cours d'exécution");
        });

        // Créer un pool de threads statiques avec Executors.newFixedThreadPool
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        // Soumettre des tâches à exécuter avec le pool de threads
        fixedThreadPool.execute(() -> {
            System.out.println("Tâche 3 en cours d'exécution");
        });
        fixedThreadPool.execute(() -> {
            System.out.println("Tâche 4 en cours d'exécution");
        });

        // Arrêter les pools de threads
        dynamicThreadPool.shutdown();
        fixedThreadPool.shutdown();
    }
}
