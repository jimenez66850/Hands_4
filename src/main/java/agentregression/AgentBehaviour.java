/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentregression;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author luisjimenezmendoza
 */
public class AgentBehaviour extends Behaviour{
    private SlrEcuation mainEcuation = new SlrEcuation();
    @Override
    public void action() {
        mainEcuation.runSlrEcuation();
    }

    @Override
    public boolean done() {
        return mainEcuation.isFinished();
    }
    
}
