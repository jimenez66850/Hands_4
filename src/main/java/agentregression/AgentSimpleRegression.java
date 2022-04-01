/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentregression;

import jade.core.Agent;

/**
 *
 * @author luisjimenezmendoza
 */
public class AgentSimpleRegression extends Agent{

    @Override
    protected void takeDown() {
        doDelete();
    }

    @Override
    protected void setup() {
        System.out.println("Hola, soy "+ getAID()+" y acabo de iniciar");
        addBehaviour(new AgentBehaviour());
    }
    
}
