//package taxiApp.repository;
//
//import taxiApp.core.Message;
//import taxiApp.core.UserRole;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//// singleton
//public class MessageRepository extends TaxiItemRepository<Message>{
//
//    private static MessageRepository messageRepository = null;
//    private MessageRepository() {}
//
//    public static MessageRepository getSingleton() {
//        if (messageRepository == null)
//            messageRepository = new MessageRepository();
//        return messageRepository;
//    }
//
//    public List<Message> getUserMessages(int userId, UserRole userRole, boolean withUnread) {
//        return entities.stream()
//                .filter(message -> message.getRecipientId() != null &&
//                        message.getRecipientId() == userId &&
//                        message.getRecipientRole() == userRole &&
//                        (withUnread || !message.isRead()))
//                .collect(Collectors.toList());
//    }
//
//    public List<Message> getRoleMessages(UserRole role, boolean withUnread) {
//        return entities.stream()
//                .filter(message -> message.getRecipientRole() == role &&
//                        (withUnread || !message.isRead()) &&
//                        message.getRecipientId() == null)
//                .collect(Collectors.toList());
//    }
//
////    @Override
////    public Message getById(int id) {
////        return null;
////    }
////
////    @Override
////    public void addNew(Message item) {
////        entities.add(item);
////        // todo go to DB
////    }
////
////    @Override
////    public int getUnusedId() {
////        return 0;
////    }
//}
