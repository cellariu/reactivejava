import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<Integer> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //  the subscriber informs the Subscription that is ready to accept one item
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("Received item: " + item);
        //  After the subscriber received one item,
        //  it reiterates to the subscription that is ready to accept another item.
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error occurred: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Print subscriber is complete");
    }
}
