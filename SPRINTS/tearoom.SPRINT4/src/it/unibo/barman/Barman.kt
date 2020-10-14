/* Generated by AN DISI Unibo */ 
package it.unibo.barman

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Barman ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var CTABLE = 0
				var CTEA = ""
				val bJson = json.BarmanJson()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						discardMessages = false
						println("  Barman | INIT  ")
					}
					 transition( edgeName="goto",targetState="waitForNewOrder", cond=doswitch() )
				}	 
				state("waitForNewOrder") { //this:State
					action { //it:State
						println("  Barman | Waiting Order  ")
						
									CTABLE = 0
									bJson.setBusy(false)
									bJson.setPreparingForTable(-1)
									bJson.setPreparingOrder("")
									bJson.setOrderReadyTable(-1)
									bJson.setOrderReady(false)
						updateResourceRep(bJson.toJson() 
						)
					}
					 transition(edgeName="t032",targetState="prepare",cond=whenDispatch("sendOrder"))
				}	 
				state("prepare") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("sendOrder(TEA,TABLE)"), Term.createTerm("sendOrder(TEA,TABLE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("  Barman | Making tea ")
								
												CTABLE = payloadArg(1).toString().toInt()
												CTEA = payloadArg(0).toString()
												bJson.setBusy(true)
												bJson.setPreparingForTable(CTABLE)
												bJson.setPreparingOrder(CTEA)
								updateResourceRep(bJson.toJson() 
								)
						}
						delay(2000) 
						forward("orderReady", "orderReady(tea,$CTABLE)" ,"waiter" ) 
						
										bJson.setPreparingForTable(-1)
										bJson.setPreparingOrder("")
										bJson.setOrderReadyTable(CTABLE)
										bJson.setOrderReady(true)
						updateResourceRep(bJson.toJson() 
						)
						println("  Barman | Tea ready to be served ")
					}
					 transition( edgeName="goto",targetState="waitForNewOrder", cond=doswitch() )
				}	 
			}
		}
}
