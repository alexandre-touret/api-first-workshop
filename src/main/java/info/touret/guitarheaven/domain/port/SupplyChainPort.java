package info.touret.guitarheaven.domain.port;

/**
 * Interacts with the SupplyChain Platform
 */
public interface SupplyChainPort {
    /**
     * @param guitarName The name of the guitar to get
     * @param quantity   the number of guitars
     */
    String requestForAdditionalGuitars(String guitarName, int quantity);
}
