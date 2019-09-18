package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        @Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	public void printNotEnough() {
                machine.insertMoney(10);
		assertEquals("Il ne fallait pas imprimer ce billet", machine.printTicket(),false);
        }
        @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
	public void printEnough() {
                machine.insertMoney(60);
		assertEquals("Il fallait imprimer ce billet", machine.printTicket(),true);
        }
        @Test
        // S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	public void verifDecrement() {
                machine.insertMoney(60);
		machine.printTicket();
                assertEquals("La valeur n'a pas ete bien decremente", 10,machine.getBalance());
        }
        @Test
        // S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	public void collectMaj() {
                machine.insertMoney(60);
                assertEquals("Total changé avant impression", 0,machine.getTotal());
		machine.printTicket();
                assertEquals("On a pas gagné de sous, ya un problème", 0,machine.getTotal());
        }
        @Test
        // S7 : refund() rend correctement la monnaie
	public void refundTest() {
                machine.insertMoney(60);
                assertEquals("Refund a un problème", 60,machine.refund());
        }
        @Test
        // S8 : refund() remet la balance à zéro
	public void refundReset() {
                machine.insertMoney(60);
                machine.refund();
                assertEquals("Refund ne reset pas la balance", 0,machine.getBalance());
        } 
        @Test
        // S9 : on ne peut pas insérer un montant négatif
	public void notNegMontant() {
            try {
                machine.insertMoney(-50);
                fail("Alors, les pièces négatives ça n'existe pas ...");
            }
            catch (IllegalArgumentException arg){
                
            }
        }
        @Test
        // S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	public void testPrixNeg() {
            
            try {
                TicketMachine mach = new TicketMachine(-10);
                fail("Alors, les ticket avec un prix négatif ça n'existe pas ...");
            }
            catch (IllegalArgumentException arg){
                
            }

        }
}
