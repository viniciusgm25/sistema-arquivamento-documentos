/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
package telas;

// Importações necessárias para a máscara e para o JPA
import controles.UpperCaseDocumentFilter; // Esta não é mais usada, mas mantenho caso você use em outro lugar
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory; // Importação importante
import javax.swing.JFormattedTextField; // Importação para a máscara
import javax.swing.text.MaskFormatter; // Importação para a máscara

public class TCadastroDocumentos extends javax.swing.JFrame {

    // --- MELHORIA DE PERFORMANCE ---
    // A EntityManagerFactory (EMF) é criada APENAS UMA VEZ quando a classe é carregada.
    // Isso é muito mais rápido do que criar uma nova a cada clique.
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UP");

    // Variável para guardar a pasta raiz selecionada
    private String caminhoBaseSelecionado;

    public TCadastroDocumentos() {
        initComponents(); // Este método agora inicializa o campo com máscara

        // Define um local padrão ao iniciar
        caminhoBaseSelecionado = "C:\\AlunosEgressos\\";
        txtCaminhoBase.setText(caminhoBaseSelecionado);

        // Garante que a pasta raiz padrão exista
        new File(caminhoBaseSelecionado).mkdirs();

        // --- MELHORIA DE FECHAMENTO ---
        // Adiciona um "ouvinte" para fechar a EntityManagerFactory (emf)
        // de forma segura quando esta janela for fechada.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (emf.isOpen()) {
                    emf.close();
                    System.out.println("EntityManagerFactory fechada com sucesso.");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        // --- ALTERAÇÃO PARA MÁSCARA DE CPF ---
        // Trocamos o JTextField por um JFormattedTextField com máscara
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_'); // Caractere que aparece nos espaços vazios
            txtCpf = new JFormattedTextField(cpfMask);
        } catch (java.text.ParseException ex) {
            // Em caso de erro, cria um campo normal (fallback)
            ex.printStackTrace();
            txtCpf = new JFormattedTextField();
            System.err.println("Erro ao criar máscara de CPF. Usando campo padrão.");
        }
        // --- FIM DA ALTERAÇÃO ---

        btnAnexar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCaminhoBase = new javax.swing.JTextField();
        btnSelecionarPasta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Upload de Documentos de Alunos");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("CPF do Aluno:");

        // (O restante do seu código initComponents permanece o mesmo)
        // ... (todo o código de layout gerado pelo NetBeans) ...
        btnAnexar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAnexar.setText("Selecionar e Enviar Documento");
        btnAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexarActionPerformed(evt);
            }
        });

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        jLabel2.setText("Histórico de envios:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Salvar na Pasta:");

        txtCaminhoBase.setEditable(false);
        txtCaminhoBase.setBackground(new java.awt.Color(235, 235, 235));

        btnSelecionarPasta.setText("Selecionar Pasta...");
        btnSelecionarPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarPastaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnAnexar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtCaminhoBase, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnSelecionarPasta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtCaminhoBase, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSelecionarPasta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAnexar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void btnSelecionarPastaActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser(caminhoBaseSelecionado);
        fileChooser.setDialogTitle("Selecione a pasta raiz para salvar os documentos");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File pasta = fileChooser.getSelectedFile();
            caminhoBaseSelecionado = pasta.getAbsolutePath() + File.separator;
            txtCaminhoBase.setText(caminhoBaseSelecionado);
            log("Pasta base alterada para: " + caminhoBaseSelecionado);
            new File(caminhoBaseSelecionado).mkdirs();
        }
    }

    /**
     * Verifica se um CPF já está cadastrado no banco de dados. USA A
     * EntityManagerFactory (emf) ESTÁTICA PARA ALTA PERFORMANCE.
     */
    private boolean verificarCPFNoBanco(String cpf) {
        EntityManager em = null; // O EntityManager é leve e rápido de criar/fechar
        try {
            // 2. Cria o EntityManager A PARTIR DA FÁBRICA (emf)
            em = emf.createEntityManager();

            Long count = em.createQuery(
                    "SELECT COUNT(a) FROM Aluno a WHERE a.cpf = :cpf", Long.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();

            return count > 0; // Se for maior que 0, o aluno existe

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao consultar o banco de dados para verificar o CPF.\n" + e.getMessage(),
                    "Erro de Banco de Dados",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (em != null) {
                em.close(); // 3. Sempre fechar o EntityManager
            }
        }
    }

    private void btnAnexarActionPerformed(java.awt.event.ActionEvent evt) {

        if (caminhoBaseSelecionado == null || caminhoBaseSelecionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma pasta base para salvar os arquivos primeiro.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Obter e limpar o CPF
        // Mesmo com a máscara, usamos o replaceAll para garantir que só venham números
        String cpfBruto = txtCpf.getText();
        String cpfLimpo = cpfBruto.replaceAll("\\D", ""); // Remove tudo que não for número

        // A máscara pode não estar preenchida, então a validação de 11 dígitos é crucial
        if (cpfLimpo.length() != 11) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Preencha os 11 dígitos.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. VALIDAÇÃO DE CPF NO BANCO
        if (!verificarCPFNoBanco(cpfLimpo)) {
            JOptionPane.showMessageDialog(this,
                    "CPF '" + cpfBruto + "' não foi encontrado no banco de dados.\n"
                    + "Por favor, cadastre o aluno antes de enviar documentos.",
                    "Aluno Não Cadastrado",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Garantir que a pasta do aluno existe (Lógica que você já tinha)
        File pastaAluno = new File(caminhoBaseSelecionado + cpfLimpo);
        if (!pastaAluno.exists()) {
            boolean criou = pastaAluno.mkdirs();
            if (criou) {
                log("Pasta criada com sucesso: " + pastaAluno.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao criar pasta: " + pastaAluno.getAbsolutePath(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // 4. Abrir o seletor de arquivos
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o documento para anexar");

        int retorno = fileChooser.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoOrigem = fileChooser.getSelectedFile();

            // --- 5. LÓGICA PARA RENOMEAR O ARQUIVO ---
            String nomeOriginal = arquivoOrigem.getName();
            String nomeSemExtensao = "";
            String extensao = "";

            // Pega a extensão (ex: ".pdf")
            int dotIndex = nomeOriginal.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < nomeOriginal.length() - 1) {
                nomeSemExtensao = nomeOriginal.substring(0, dotIndex);
                extensao = nomeOriginal.substring(dotIndex); // Guarda o ".pdf"
            } else {
                nomeSemExtensao = nomeOriginal; // Não tinha extensão
                extensao = "";
            }

            // Pergunta ao usuário o novo nome, sugerindo o nome original sem extensão
            String novoNomeBase = (String) JOptionPane.showInputDialog(
                    this,
                    "Digite o nome para salvar o arquivo:\n(A extensão \"" + extensao + "\" será mantida)",
                    "Renomear Arquivo",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    nomeSemExtensao); // Valor padrão

            // Se o usuário cancelar ou deixar em branco, para a operação
            if (novoNomeBase == null || novoNomeBase.trim().isEmpty()) {
                log("Envio cancelado pelo usuário durante a renomeação.");
                return;
            }

            // Monta o nome final
            String nomeFinal = novoNomeBase.trim() + extensao;
            // --- FIM DA LÓGICA DE RENOMEAR ---

            // Monta o destino: [Pasta Base]\[CPF]\[NomeDoArquivoFinal]
            File arquivoDestino = new File(pastaAluno, nomeFinal);

            // --- 6. VERIFICAR SE O ARQUIVO JÁ EXISTE ---
            if (arquivoDestino.exists()) {
                int resposta = JOptionPane.showConfirmDialog(
                        this,
                        "O arquivo '" + nomeFinal + "' já existe nesta pasta.\nDeseja substituí-lo?",
                        "Confirmar Substituição",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                // Se o usuário disser NÃO, para a operação
                if (resposta == JOptionPane.NO_OPTION) {
                    log("Envio cancelado. Arquivo '" + nomeFinal + "' já existe.");
                    return;
                }
            }

            try {
                // 7. Realiza a cópia do arquivo
                // (Usando REPLACE_EXISTING caso o usuário tenha dito SIM acima)
                Files.copy(arquivoOrigem.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                log("SUCESSO: Arquivo '" + nomeFinal + "' enviado para o CPF " + cpfLimpo);
                JOptionPane.showMessageDialog(this, "Documento enviado com sucesso!");

            } catch (IOException ex) {
                log("ERRO ao copiar arquivo: " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Falha ao copiar o arquivo.\nVerifique se ele não está aberto em outro programa.", "Erro de E/S", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void log(String mensagem) {
        txtLog.append(mensagem + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCadastroDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TCadastroDocumentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnSelecionarPasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCaminhoBase;
    private javax.swing.JFormattedTextField txtCpf; // <-- ATUALIZADO AQUI
    private javax.swing.JTextArea txtLog;
    // End of variables declaration
}
