package org.business.control.business.command.configuration.prestation;

import org.business.control.business.command.BusinessCommand;

public class AjouterPrestationCommand implements BusinessCommand {
    private final String libelle;

    public AjouterPrestationCommand(String libelle) {
	this.libelle = libelle;
    }

    public String getLibelle() {
	return libelle;
    }
}
