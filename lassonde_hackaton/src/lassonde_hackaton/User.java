package lassonde_hackaton;

public class User extends Person 
{
    private Order order;

    public User()
    {
        super();
    }

    public User(String[] userData)
    {
        userName = userData[0];
        email = userData[1];
        name = userData[2];
        address = userData[3];
        paymentInfo = userData[4];
        gender = userData[5];

    }
    
    public User(User u) {
    	this(u.getallInfo().split(","));
    }

    @Override
    public void setPaymentInfo(String i) 
    {
        paymentInfo = i;
    }

    @Override
    public String getPaymentInfo() {
        return paymentInfo;
    }

    @Override
    public String getallInfo() {
        // TODO Auto-generated method stub
        return  userName+","+email+","+name+","+address+","+paymentInfo+","+gender;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}