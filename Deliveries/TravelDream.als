module edu_polimi/TravelDream

/*
TravelDream, 2013 @ PoliMI (Ing.Soft.2)
autori: Dario Nesi, Leonardo Rossetti, Antonio Russo
*/

enum TipoPacchetto {PV_Predefinito, PV_Personalizzato}
enum StatoPV {Salvato, NonSalvato}
enum RuoloUtente {Amico, UtenteRegistrato, Impiegato, Amministratore}

sig Utente {
	//email : wString,
	ruolo : one RuoloUtente
}

sig Pagamento {
	pagatoDa: Utente,
	voce :  PV_Voce
}

abstract sig PV_Voce {}

sig Volo extends PV_Voce {}
sig Albergo extends PV_Voce{}
sig Escursione extends PV_Voce{}

sig PV{
	tipo: one TipoPacchetto,
	stato: one StatoPV,

	voci: set PV_Voce,

	proprietario: one Utente
}

// "Le asserzioni sono i nostri requisiti e i fatti sono il come pensiamo si comporti il modello" 


// =================================================================
// ogni pagamento può essere effettuato solo da un utente di livello adeguato
fact {
	all p : Pagamento | 	p.pagatoDa.ruolo = UtenteRegistrato or 
									p.pagatoDa.ruolo = Impiegato or 
									p.pagatoDa.ruolo = Amministratore
}
// Un utente che non è registrato non può pagare (Amico)
assert NoPagamentiDaAmico{
	no p : Pagamento | p.pagatoDa.ruolo = Amico
}

check NoPagamentiDaAmico for 30

// =================================================================
// Ogni PV ha almeno una voce
fact {
	all pv : PV | #pv.voci > 0
}

// =================================================================
// tutti i PV predefiniti provengono da Impiegati o Amministratori
fact {
	all pv : PV | pv.tipo = PV_Predefinito and (pv.proprietario.ruolo = Impiegato or pv.proprietario.ruolo = Amministratore)
}
assert NoPVpredefinitiDaUtentiRegistrati {
	no pv : PV | pv.tipo = PV_Predefinito and (pv.proprietario.ruolo = UtenteRegistrato or pv.proprietario.ruolo = Amico)
}	

// =================================================================
// il proprietario di un PV può essere un utente di livello minimo Utente Registrato
fact {
	all pv : PV | 	pv.proprietario.ruolo = UtenteRegistrato or 
						pv.proprietario.ruolo = Impiegato or 
						pv.proprietario.ruolo = Amministratore
}

// =================================================================
// ogni voce registrata è associata ad un solo PV
fact {
	all voce : PV_Voce | one pv : PV | voce in pv.voci
}
// Non esistono voci che appartengono a più pacchetti viaggio
assert NoVociPiuPadri{
	no voce : PV_Voce | some pv1, pv2 : PV | pv1 != pv2 and voce in pv1.voci and voce in pv2.voci
}
// Non esistono voci che non appartengono ad alcun PV
assert VociSenzaRiferimentoaPV {
	no v : PV_Voce | no pv : PV | v in pv.voci
}
check NoVociPiuPadri for 2
check VociSenzaRiferimentoaPV for 30

// =================================================================
// ogni voce ha al più un solo pagamento
fact {
	all v : PV_Voce | lone pagamento : Pagamento | pagamento.voce = v
}

// =================================================================
// Non si possono avere pagamenti se il PV non è salvato
fact {
	no pv : PV | pv.stato = NonSalvato and some p : Pagamento | p.voce in pv.voci
}

// =================================================================

pred show()
{
#PV = 1
/*#Volo = 1
#Albergo = 1
#Escursione = 1*/
}

run show for 3
