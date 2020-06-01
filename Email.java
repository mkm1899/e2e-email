import java.io.ObjectOutputStream;
import java.io.Serializable;

final class Email implements Serializable{
    private static final long serialVersionUID = 435849834234861L;
    private String to;          //recipient 
    private String from;
    private String subject;
    private String message;

    public Email(){
        
    }

    public Email(String to, String from, String subject, String message){
        this.to = to; 
        this.from = from;
        this.subject = subject;
        this.message = message;
    }

    public static void sendEmail(ObjectOutputStream out, Email email){
        Request
    }
    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }
    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString(){
        String x = "From: " + this.from +"\nTo: "+ this.to + "\nSubject: " + this.subject + "\n\n" + this.message;
        return x; 
    }

}