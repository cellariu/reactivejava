import java.util.concurrent.SubmissionPublisher;

public class Plus10SubmissionExample {

    public static void main(String[] args) throws InterruptedException {

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        Plus10Processor processor = new Plus10Processor();

        PrintSubscriber printSubscriber = new PrintSubscriber();

        publisher.subscribe(processor);
        processor.subscribe(printSubscriber);

        System.out.println("Submitting items...");

        for (int i = 0; i < 10; i++) {
            publisher.submit(i);
        }

        Thread.sleep(1000);

        publisher.close();
    }
}
