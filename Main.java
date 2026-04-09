import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.File;

public class Main extends JFrame {
    private JTextField nameField;
    private JButton drawButton;
    private JLabel resultLabel;
    private JTextArea historyArea;
    private Random rand = new Random();
    private String[] items = {"桜", "バスケットボール", "時計", "猫", "本"};

    public Main() {
        setTitle("おみくじアプリ");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(15);
        drawButton = new JButton("おみくじを引く");
        topPanel.add(new JLabel("名前:"));
        topPanel.add(nameField);
        topPanel.add(drawButton);

        resultLabel = new JLabel("結果がここに表示されます", SwingConstants.CENTER);
        resultLabel.setFont(new Font("MS ゴシック", Font.BOLD, 16));
        resultLabel.setPreferredSize(new Dimension(400, 50));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("履歴"));

        add(topPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        setLocationRelativeTo(null); // 起動時に画面中央に配置
        drawButton.addActionListener(e -> drawOmikuji());

        setVisible(true);
    }

    private void drawOmikuji() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            resultLabel.setText("名前を入力してください");
            return;
        }

        // 音声を再生
        playSound("draw.wav");

        int num = rand.nextInt(100); // 0-99 のいずれか
        String result;
        Color resultColor;

        if (num < 10) {            // 10%
            result = "大吉";
            resultColor = Color.RED;
        } else if (num < 30) {      // 20%
            result = "中吉";
            resultColor = new Color(255, 140, 0); // オレンジ
        } else if (num < 70) {      // 40%
            result = "小吉";
            resultColor = Color.BLUE;
        } else {                    // 30%
            result = "凶";
            resultColor = Color.GRAY;
        }

        // 詳細な運勢（星の数）
        String loveLuck = "★".repeat(rand.nextInt(5) + 1);

        int itemIndex = rand.nextInt(items.length);
        String luckyItem = items[itemIndex];

        String resultText = String.format("%sさんの結果: %s / 恋愛運: %s / アイテム: %s", name, result, loveLuck, luckyItem);
        resultLabel.setText(resultText);
        resultLabel.setForeground(resultColor);
        historyArea.append(resultText + "\n");
        historyArea.setCaretPosition(historyArea.getDocument().getLength()); // 履歴が追加されたら自動スクロール
    }

    /**
     * 指定された音声ファイルを再生するヘルパーメソッド
     */
    private void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            if (soundFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                // 再生終了後にリソースを解放
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
                clip.start();
            }
        } catch (Exception e) {
            System.err.println("音源の再生に失敗しました: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}