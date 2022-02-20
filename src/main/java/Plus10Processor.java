import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Plus10Processor extends SubmissionPublisher<Integer> implements Flow.Subscriber<Integer> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //  the subscriber informs the Subscription that is ready to accept one item
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        submit(item + 10);
        //  After the subscriber received one item,
        //  it reiterates to the subscription that is ready to accept another item.
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("PlusTenProcessor completed");
        close();
    }
}
