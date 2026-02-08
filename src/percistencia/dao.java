package persistencia;

import controles.Aluno; // <-- IMPORTANTE: Adicionado para verificar o CPF
import controles.Curso;
import controles.CursoPK;
import controles.Turma;
import controles.TurmaPK;
import java.util.List;
import javax.persistence.*;

public class dao {

    // A "fábrica" é criada APENAS UMA VEZ. Perfeito.
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UP");

    /**
     * Salva ou atualiza um objeto, limpando o CPF se for um Aluno.
     */
    public static void salvar(Object objeto) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            ent.getTransaction().begin();

            // --- CORREÇÃO PARA O SEU PROBLEMA DO CPF ---
            // Antes de salvar, verifica se o objeto é um Aluno
            if (objeto instanceof Aluno) {
                Aluno aluno = (Aluno) objeto; // Converte o "Object" para "Aluno"
                if (aluno.getCpf() != null) {
                    // Limpa a máscara
                    String cpfLimpo = aluno.getCpf().replaceAll("\\D", "");
                    // Salva o CPF limpo de volta no objeto
                    aluno.setCpf(cpfLimpo);
                }
            }
            // --- FIM DA CORREÇÃO DO CPF ---

            ent.merge(objeto); // Agora salva o objeto (limpo, se for Aluno)
            ent.getTransaction().commit();

        } catch (Exception e) {
            if (ent != null && ent.getTransaction().isActive()) {
                ent.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Falha ao salvar no banco: " + e.getMessage(), e);
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    // --- MÉTODOS DE LISTAGEM (TODOS CORRIGIDOS) ---
    public static List listar(String nomePesquisa) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager(); // Usa a fábrica estática
            Query q = ent.createNamedQuery(nomePesquisa);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close(); // Fecha o EntityManager
            }
        }
    }

    public static List listar(String nomePesquisa, String parametro, int chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(nomePesquisa);
            q.setParameter(parametro, chave);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static List listar(String nomePesquisa, String parametro, String chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(nomePesquisa);
            q.setParameter(parametro, chave);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static List listar1Sql(String sql) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createQuery(sql);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static List listar(String nomePesquisa, String parametro1, int chave1, String parametro2, String chave2) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(nomePesquisa);
            q.setParameter(parametro1, chave1);
            q.setParameter(parametro2, chave2);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static List listar(String nomePesquisa, String parametro1, int chave1, String parametro2, String chave2, String parametro3, String chave3) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(nomePesquisa);
            q.setParameter(parametro1, chave1);
            q.setParameter(parametro2, chave2);
            q.setParameter(parametro3, chave3);
            return q.getResultList();
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    // --- MÉTODOS DE EXECUÇÃO/EXCLUSÃO (TODOS CORRIGIDOS) ---
    public static int executarSql(String sql) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNativeQuery(sql);
            ent.getTransaction().begin();
            int r = q.executeUpdate();
            ent.getTransaction().commit();
            return r;
        } catch (Exception e) {
            if (ent != null && ent.getTransaction().isActive()) {
                ent.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao executar SQL: " + e.getMessage(), e);
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static void excluir(Object objeto) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            ent.getTransaction().begin();
            Object ref = ent.find(objeto.getClass(), ent.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(objeto));

            if (ref != null) {
                ent.remove(ref);
            } else {
                throw new javax.persistence.EntityNotFoundException("OBJETO NÃO ENCONTRADO");
            }
            ent.getTransaction().commit();
        } catch (Exception e) {
            if (ent != null && ent.getTransaction().isActive()) {
                ent.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao excluir: " + e.getMessage(), e);
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static void excluir(String pesquisa, String parametro, int chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            ent.getTransaction().begin();
            Query q = ent.createNamedQuery(pesquisa);
            q.setParameter(parametro, chave);
            Object objeto = q.getSingleResult();
            ent.remove(objeto);
            ent.getTransaction().commit();
        } catch (Exception e) {
            if (ent != null && ent.getTransaction().isActive()) {
                ent.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao excluir: " + e.getMessage(), e);
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static void excluir(String pesquisa, String parametro, String chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            ent.getTransaction().begin();
            Query q = ent.createNamedQuery(pesquisa);
            q.setParameter(parametro, chave);
            Object objeto = q.getSingleResult();
            ent.remove(objeto);
            ent.getTransaction().commit();
        } catch (Exception e) {
            if (ent != null && ent.getTransaction().isActive()) {
                ent.getTransaction().rollback();
            }
            throw new RuntimeException("Falha ao excluir: " + e.getMessage(), e);
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    // --- MÉTODOS DE CONSULTA (TODOS CORRIGIDOS) ---
    public static Object consultar(String pesquisa, String parametro, int chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(pesquisa);
            q.setParameter(parametro, chave);
            return q.getSingleResult();
        } catch (NoResultException e) { // É melhor tratar NoResultException
            return null; // Retorna nulo se não achar nada
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static Object consultar(String pesquisa, String parametro, String chave) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            Query q = ent.createNamedQuery(pesquisa);
            q.setParameter(parametro, chave);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna nulo se não achar nada
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static Curso consultar(Curso objeto) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            return ent.find(Curso.class, objeto.getCursoPK());
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static Turma consultar(Turma objeto) {
        EntityManager ent = null;
        try {
            ent = emf.createEntityManager();
            return ent.find(Turma.class, objeto.getTurmaPK());
        } finally {
            if (ent != null) {
                ent.close();
            }
        }
    }

    public static Object findById(CursoPK pk) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Curso.class, pk);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Object findById(TurmaPK pk) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(Turma.class, pk);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
