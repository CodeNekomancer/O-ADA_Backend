package com.github.CodeNekomancer.OADA_Backend.model.Universe;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Universe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universe_id;

    private String serverId;
    private String name;
    private Integer number;
    private String language;
    private String timezone;
    private String timezoneOffset;
    private String domain;
    private String version;
    private Integer speed;
    private Integer speedFleet;
    private Integer galaxies;
    private Integer systems;
    private Boolean acs;
    private Boolean rapidFire;
    private Boolean defToTF;
    private Float debrisFactor;
    private Float debrisFactorDef;
    private Float repairFactor;
    private Long newbieProtectionLimit;
    private Long newbieProtectionHigh;
    private Long topScore;
    private Integer bonusFields;
    private Boolean donutGalaxy;
    private Boolean donutSystem;
    private Boolean wfEnabled;
    private Long wfMinimumRessLost;
    private Integer wfMinimumLossPercentage;
    private Integer wfBasicPercentageRepairable;
    private Float globalDeuteriumSaveFactor;
    private Integer bashlimit;
    private Integer probeCargo;
    private Integer researchDurationDivisor;
    private Integer darkMatterNewAcount;
    private Integer cargoHyperspaceTechMultiplier;
    private Boolean marketplaceEnabled;
    private Float marketplaceBasicTradeRatioMetal;
    private Float marketplaceBasicTradeRatioCrystal;
    private Float marketplaceBasicTradeRatioDeuterium;
    private Float marketplacePriceRangeLower;
    private Float marketplacePriceRangeUpper;
    private Float marketplaceTaxNormalUser;
    private Float marketplaceTaxAdmiral;
    private Float marketplaceTaxCancelOffer;
    private Float marketplaceTaxNotSold;
    private Integer marketplaceOfferTimeout;
    private Boolean characterClassesEnabled;
    private Float minerBonusResourceProduction;
    private Float minerBonusFasterTradingShips;
    private Float minerBonusIncreasedCargoCapacityForTradingShips;
    private Integer minerBonusAdditionalFleetSlots;
    private Integer minerBonusAdditionalMarketSlots;
    private Float minerBonusAdditionalCrawler;
    private Float minerBonusMaxCrawler;
    private Float minerBonusEnergy;
    private Float minerBonusOverloadCrawler;
    private Float resourceBuggyProductionBoost;
    private Float resourceBuggyMaxProductionBoost;
    private Integer resourceBuggyEnergyConsumptionPerUnit;
    private Integer warriorBonusFasterCombatShips;
    private Integer warriorBonusFasterRecyclers;
    private Float warriorBonusFuelConsumption;
    private Float warriorBonusRecyclerFuelConsumption;
    private Float warriorBonusRecyclerCargoCapacity;
    private Integer warriorBonusAdditionalFleetSlots;
    private Integer warriorBonusAdditionalMoonFields;
    private Boolean warriorBonusFleetHalfSpeed;
    private Boolean warriorBonusAttackerWreckfield;
    private Float combatDebrisFieldLimit;
    private Float explorerBonusIncreasedResearchSpeed;
    private Float explorerBonusIncreasedExpeditionOutcome;
    private Float explorerBonusLargerPlanets;
    private Integer explorerUnitItemsPerDay;
    private Float explorerBonusPhalanxRange;
    private Boolean explorerBonusPlunderInactive;
    private Float explorerBonusExpeditionEnemyReduction;
    private Integer explorerBonusAdditionalExpeditionSlots;
    private Float resourceProductionIncreaseCrystalDefault;
    private Float resourceProductionIncreaseCrystalPos1;
    private Float resourceProductionIncreaseCrystalPos2;
    private Float resourceProductionIncreaseCrystalPos3;
    private Float exodusRatioMetal;
    private Float exodusRatioCrystal;
    private Float exodusRatioDeuterium;

    @OneToMany(mappedBy = "itsUniverse")
    private List<UAcc> universeAccounts;

    public Universe(String serverId) {
        this.serverId = serverId;
    }
}
