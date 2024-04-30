package demoJDBC;

import ma.projet.beans.Client;
import ma.projet.service.ClientService;

public class Test {
    public static void main(String[] args) {
        ClientService cs = new ClientService();

        cs.create(new Client("SAFI", "ali"));
        cs.create(new Client("ALAOUI", "widane"));
        cs.create(new Client("RAMI", "amine"));
        cs.create(new Client("ALAMI", "kamal"));
        cs.create(new Client("SELAMI", "mohamed"));

        Client c = cs.findById(3);
        if (c != null) {
            System.out.println("Client trouvé : " + c.getNom() + " " + c.getPrenom());
        } else {
            System.out.println("Client non trouvé.");
        }

        if (cs.delete(cs.findById(3))) {
            System.out.println("Client supprimé avec succès.");
        } else {
            System.out.println("Impossible de supprimer le client.");
        }

        Client cc = cs.findById(2);
        if (cc != null) {
            cc.setNom("nouveau nom");
            cc.setPrenom("nouveau prénom");
            if (cs.update(cc)) {
                System.out.println("Client mis à jour avec succès.");
            } else {
                System.out.println("Impossible de mettre à jour le client.");
            }
        } else {
            System.out.println("Client non trouvé.");
        }

        System.out.println("Liste des clients :");
        for (Client cl : cs.findAll()) {
            System.out.println(cl.getNom() + " " + cl.getPrenom());
        }
    }
}
