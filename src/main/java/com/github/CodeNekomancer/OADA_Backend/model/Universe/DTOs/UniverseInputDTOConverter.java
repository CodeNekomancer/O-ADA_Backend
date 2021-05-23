package com.github.CodeNekomancer.OADA_Backend.model.Universe.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import org.springframework.stereotype.Component;

@Component
public class UniverseInputDTOConverter {
    public Universe convertUniverseInputDTOToUniverse(Universe universe, UniverseInputDTO universeInputDTO) {
        // universe.setServerId(universeInputDTO.getServerId());

        universe.setName(universeInputDTO.getName());
        universe.setNumber(universeInputDTO.getNumber());
        universe.setLanguage(universeInputDTO.getLanguage());
        universe.setTimezone(universeInputDTO.getTimezone());
        universe.setTimezoneOffset(universeInputDTO.getTimezoneOffset());
        universe.setDomain(universeInputDTO.getDomain());
        universe.setVersion(universeInputDTO.getVersion());
        universe.setSpeed(universeInputDTO.getSpeed());
        universe.setSpeedFleet(universeInputDTO.getSpeedFleet());
        universe.setGalaxies(universeInputDTO.getGalaxies());
        universe.setSystems(universeInputDTO.getSystems());
        universe.setAcs(universeInputDTO.getAcs());
        universe.setRapidFire(universeInputDTO.getRapidFire());
        universe.setDefToTF(universeInputDTO.getDefToTF());
        universe.setDebrisFactor(universeInputDTO.getDebrisFactor());
        universe.setDebrisFactorDef(universeInputDTO.getDebrisFactorDef());
        universe.setRepairFactor(universeInputDTO.getRepairFactor());
        universe.setNewbieProtectionLimit(universeInputDTO.getNewbieProtectionLimit());
        universe.setNewbieProtectionHigh(universeInputDTO.getNewbieProtectionHigh());
        universe.setTopScore(universeInputDTO.getTopScore());
        universe.setBonusFields(universeInputDTO.getBonusFields());
        universe.setDonutGalaxy(universeInputDTO.getDonutGalaxy());
        universe.setDonutSystem(universeInputDTO.getDonutSystem());
        universe.setWfEnabled(universeInputDTO.getWfEnabled());
        universe.setWfMinimumRessLost(universeInputDTO.getWfMinimumRessLost());
        universe.setWfMinimumLossPercentage(universeInputDTO.getWfMinimumLossPercentage());
        universe.setWfBasicPercentageRepairable(universeInputDTO.getWfBasicPercentageRepairable());
        universe.setGlobalDeuteriumSaveFactor(universeInputDTO.getGlobalDeuteriumSaveFactor());
        universe.setBashlimit(universeInputDTO.getBashlimit());
        universe.setProbeCargo(universeInputDTO.getProbeCargo());
        universe.setResearchDurationDivisor(universeInputDTO.getResearchDurationDivisor());
        universe.setDarkMatterNewAcount(universeInputDTO.getDarkMatterNewAcount());
        universe.setCargoHyperspaceTechMultiplier(universeInputDTO.getCargoHyperspaceTechMultiplier());
        universe.setMarketplaceEnabled(universeInputDTO.getMarketplaceEnabled());
        universe.setMarketplaceBasicTradeRatioMetal(universeInputDTO.getMarketplaceBasicTradeRatioMetal());
        universe.setMarketplaceBasicTradeRatioCrystal(universeInputDTO.getMarketplaceBasicTradeRatioCrystal());
        universe.setMarketplaceBasicTradeRatioDeuterium(universeInputDTO.getMarketplaceBasicTradeRatioDeuterium());
        universe.setMarketplacePriceRangeLower(universeInputDTO.getMarketplacePriceRangeLower());
        universe.setMarketplacePriceRangeUpper(universeInputDTO.getMarketplacePriceRangeUpper());
        universe.setMarketplaceTaxNormalUser(universeInputDTO.getMarketplaceTaxNormalUser());
        universe.setMarketplaceTaxAdmiral(universeInputDTO.getMarketplaceTaxAdmiral());
        universe.setMarketplaceTaxCancelOffer(universeInputDTO.getMarketplaceTaxCancelOffer());
        universe.setMarketplaceTaxNotSold(universeInputDTO.getMarketplaceTaxNotSold());
        universe.setMarketplaceOfferTimeout(universeInputDTO.getMarketplaceOfferTimeout());
        universe.setCharacterClassesEnabled(universeInputDTO.getCharacterClassesEnabled());
        universe.setMinerBonusResourceProduction(universeInputDTO.getMinerBonusResourceProduction());
        universe.setMinerBonusFasterTradingShips(universeInputDTO.getMinerBonusFasterTradingShips());
        universe.setMinerBonusIncreasedCargoCapacityForTradingShips(universeInputDTO.getMinerBonusIncreasedCargoCapacityForTradingShips());
        universe.setMinerBonusAdditionalFleetSlots(universeInputDTO.getMinerBonusAdditionalFleetSlots());
        universe.setMinerBonusAdditionalMarketSlots(universeInputDTO.getMinerBonusAdditionalMarketSlots());
        universe.setMinerBonusAdditionalCrawler(universeInputDTO.getMinerBonusAdditionalCrawler());
        universe.setMinerBonusMaxCrawler(universeInputDTO.getMinerBonusMaxCrawler());
        universe.setMinerBonusEnergy(universeInputDTO.getMinerBonusEnergy());
        universe.setMinerBonusOverloadCrawler(universeInputDTO.getMinerBonusOverloadCrawler());
        universe.setResourceBuggyProductionBoost(universeInputDTO.getResourceBuggyProductionBoost());
        universe.setResourceBuggyMaxProductionBoost(universeInputDTO.getResourceBuggyMaxProductionBoost());
        universe.setResourceBuggyEnergyConsumptionPerUnit(universeInputDTO.getResourceBuggyEnergyConsumptionPerUnit());
        universe.setWarriorBonusFasterCombatShips(universeInputDTO.getWarriorBonusFasterCombatShips());
        universe.setWarriorBonusFasterRecyclers(universeInputDTO.getWarriorBonusFasterRecyclers());
        universe.setWarriorBonusFuelConsumption(universeInputDTO.getWarriorBonusFuelConsumption());
        universe.setWarriorBonusRecyclerFuelConsumption(universeInputDTO.getWarriorBonusRecyclerFuelConsumption());
        universe.setWarriorBonusRecyclerCargoCapacity(universeInputDTO.getWarriorBonusRecyclerCargoCapacity());
        universe.setWarriorBonusAdditionalFleetSlots(universeInputDTO.getWarriorBonusAdditionalFleetSlots());
        universe.setWarriorBonusAdditionalMoonFields(universeInputDTO.getWarriorBonusAdditionalMoonFields());
        universe.setWarriorBonusFleetHalfSpeed(universeInputDTO.getWarriorBonusFleetHalfSpeed());
        universe.setWarriorBonusAttackerWreckfield(universeInputDTO.getWarriorBonusAttackerWreckfield());
        universe.setCombatDebrisFieldLimit(universeInputDTO.getCombatDebrisFieldLimit());
        universe.setExplorerBonusIncreasedResearchSpeed(universeInputDTO.getExplorerBonusIncreasedResearchSpeed());
        universe.setExplorerBonusIncreasedExpeditionOutcome(universeInputDTO.getExplorerBonusIncreasedExpeditionOutcome());
        universe.setExplorerBonusLargerPlanets(universeInputDTO.getExplorerBonusLargerPlanets());
        universe.setExplorerUnitItemsPerDay(universeInputDTO.getExplorerUnitItemsPerDay());
        universe.setExplorerBonusPhalanxRange(universeInputDTO.getExplorerBonusPhalanxRange());
        universe.setExplorerBonusPlunderInactive(universeInputDTO.getExplorerBonusPlunderInactive());
        universe.setExplorerBonusExpeditionEnemyReduction(universeInputDTO.getExplorerBonusExpeditionEnemyReduction());
        universe.setExplorerBonusAdditionalExpeditionSlots(universeInputDTO.getExplorerBonusAdditionalExpeditionSlots());
        universe.setResourceProductionIncreaseCrystalDefault(universeInputDTO.getResourceProductionIncreaseCrystalDefault());
        universe.setResourceProductionIncreaseCrystalPos1(universeInputDTO.getResourceProductionIncreaseCrystalPos1());
        universe.setResourceProductionIncreaseCrystalPos2(universeInputDTO.getResourceProductionIncreaseCrystalPos2());
        universe.setResourceProductionIncreaseCrystalPos3(universeInputDTO.getResourceProductionIncreaseCrystalPos3());
        universe.setExodusRatioMetal(universeInputDTO.getExodusRatioMetal());
        universe.setExodusRatioCrystal(universeInputDTO.getExodusRatioCrystal());
        universe.setExodusRatioDeuterium(universeInputDTO.getExodusRatioDeuterium());

        return universe;
    }
}
