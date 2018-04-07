package cn.liujinnan.weather.sender;

public class EmailMesssage extends Message{

    private static final String DEFAULT_NICKNAME = "WeatherDog";

    private String title;

    private String receiver;

    private String nickname;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder extends Message{

        private String title;

        private String receiver;

        private String nickname;

        public Builder buildTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder buildReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder buildNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder buildContext(String context) {
            this.setContext(context);
            return this;
        }

        public EmailMesssage build() {
            EmailMesssage emailMesssage = new EmailMesssage();
            emailMesssage.setContext(this.getContext());
            emailMesssage.setTitle(this.title);
            emailMesssage.setNickname(this.nickname);
            emailMesssage.setReceiver(this.receiver);
            return emailMesssage;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "EmailMesssage{" +
                "title='" + title + '\'' +
                ", receiver='" + receiver + '\'' +
                ", nickname='" + nickname + '\'' +
                ", context='" + this.getContext() + '\'' +
                "} " + super.toString();
    }
}
