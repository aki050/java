import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main extends JFrame {
    private JTextField nameField;
    private JButton drawButton;
    private JLabel resultLabel;
    private Random rand = new Random();
    private String[] items = {"桜", "バスケットボール", "時計", "猫", "本"};

    public Main() {
        setTitle("おみくじアプリ");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        nameField = new JTextField(15);
        drawButton = new JButton("おみくじを引く");
        resultLabel = new JLabel("結果がここに表示されます");

        add(new JLabel("名前:"));
        add(nameField);
        add(drawButton);
        add(resultLabel);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawOmikuji();
            }
        });

        setVisible(true);
    }

    private void drawOmikuji() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            resultLabel.setText("名前を入力してください");
            return;
        }

        int num = rand.nextInt(100); // 0-99 のいずれか

        String result;
        if (num < 10) {            // 10%
            result = "大吉";
        } else if (num < 30) {      // 20%
            result = "中吉";
        } else if (num < 70) {      // 40%
            result = "小吉";
        } else {                    // 30%
            result = "凶";
        }

        int itemIndex = rand.nextInt(items.length);
        String luckyItem = items[itemIndex];

        resultLabel.setText(name + "さんのおみくじ結果: " + result + " ラッキーアイテム: " + luckyItem);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}