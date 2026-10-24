
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Task3 extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private Map<String, String> faq = new LinkedHashMap<>();

    public Task3() {
        trainBot();

        setTitle("AI Chatbot");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Chat display area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // User input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 15));

        // Send button
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        chatArea.append("Bot: Hello! Welcome to my AI chatbot.\n");
        chatArea.append("Bot: Ask me about Java, AI, NLP or ML.\n\n");

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    // FAQ knowledge base
    private void trainBot() {
        faq.put("hello", "Hello! How can I help you?");
        faq.put("hi", "Hi! Nice to meet you.");
        faq.put("your name", "I am a Java AI chatbot.");
        faq.put("how are you", "I am doing well! Thanks for asking.");

        faq.put("what is java",
                "Java is an object-oriented programming language.");

        faq.put("what is ai",
                "AI enables machines to perform tasks requiring human intelligence.");

        faq.put("what is nlp",
                "NLP helps computers process and understand human language.");

        faq.put("what is machine learning",
                "Machine Learning enables computers to learn patterns from data.");

        faq.put("who created you",
                "I am a sample chatbot developed using Java.");

        faq.put("what can you do",
                "I can answer questions about Java, AI, NLP and Machine Learning.");

        faq.put("thank you", "You're welcome!");
        faq.put("bye", "Goodbye! Have a nice day.");
    }

    // Basic NLP: normalize user text
    private String cleanText(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    // Rule-based answers and FAQ matching
    private String getResponse(String input) {
        String question = cleanText(input);

        if (question.isEmpty()) {
            return "Please enter a question.";
        }

        // Check question intent before checking greetings
        if (question.contains("how are you")) {
            return faq.get("how are you");
        }

        // Exact FAQ match
        if (faq.containsKey(question)) {
            return faq.get(question);
        }

        // Name-related questions
        if (question.contains("your name")
                || question.contains("who are you")) {
            return faq.get("your name");
        }

        // Machine Learning
        if (question.contains("machine learning")) {
            return faq.get("what is machine learning");
        }

        // Artificial Intelligence
        if (question.contains("artificial intelligence")
                || question.equals("ai")) {
            return faq.get("what is ai");
        }

        // Natural Language Processing
        if (question.contains("natural language processing")
                || question.equals("nlp")) {
            return faq.get("what is nlp");
        }

        // Java
        if (question.contains("java")) {
            return faq.get("what is java");
        }

        // Creator-related questions
        if (question.contains("who created you")) {
            return faq.get("who created you");
        }

        // Help
        if (question.contains("what can you do")
                || question.contains("help")) {
            return faq.get("what can you do");
        }

        // Thank-you messages
        if (question.contains("thank")) {
            return faq.get("thank you");
        }

        // Goodbye
        if (question.equals("bye") || question.equals("goodbye")) {
            return faq.get("bye");
        }

        // Default response
        return "Sorry, I don't know that answer yet. "
                + "Try asking about Java, AI, NLP or ML.";
    }

    // Handle real-time messages
    private void sendMessage() {
        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        chatArea.append("You: " + message + "\n");

        String response = getResponse(message);
        chatArea.append("Bot: " + response + "\n\n");

        inputField.setText("");
        inputField.requestFocusInWindow();

        // Disable input after goodbye
        String normalized = cleanText(message);

        if (normalized.equals("bye") || normalized.equals("goodbye")) {
            sendButton.setEnabled(false);
            inputField.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task3());
    }
}
