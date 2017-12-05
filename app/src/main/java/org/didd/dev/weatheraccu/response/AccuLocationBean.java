package org.didd.dev.weatheraccu.response;

import java.util.List;

/**
 * Created by Jiangxuewu on 2017/12/5.
 */

public class AccuLocationBean {

    /**
     * Version : 1
     * Key : 2332635
     * Type : City
     * Rank : 25
     * LocalizedName : 南山区
     * EnglishName : Nanshan District
     * PrimaryPostalCode :
     * Region : {"ID":"ASI","LocalizedName":"亚洲","EnglishName":"Asia"}
     * Country : {"ID":"CN","LocalizedName":"中国","EnglishName":"China"}
     * AdministrativeArea : {"ID":"44","LocalizedName":"广东省","EnglishName":"Guangdong","Level":1,"LocalizedType":"省","EnglishType":"Province","CountryID":"CN"}
     * TimeZone : {"Code":"CST","Name":"Asia/Shanghai","GmtOffset":8,"IsDaylightSaving":false,"NextOffsetChange":null}
     * GeoPosition : {"Latitude":22.536,"Longitude":113.927,"Elevation":{"Metric":{"Value":30,"Unit":"m","UnitType":5},"Imperial":{"Value":98,"Unit":"ft","UnitType":0}}}
     * IsAlias : false
     * ParentCity : {"Key":"58194","LocalizedName":"深圳","EnglishName":"Shenzhen"}
     * SupplementalAdminAreas : [{"Level":2,"LocalizedName":"深圳市","EnglishName":"Shenzhen"}]
     * DataSets : ["AirQuality","Alerts","DailyAirQualityForecast","DailyLocalIndices","PremiumAirQuality"]
     * Details : {"Key":"2332635","StationCode":"CI3221","StationGmtOffset":8,"BandMap":"CIMO","Climo":"ZB70","LocalRadar":"","MediaRegion":null,"Metar":"ZGSZ","NXMetro":"","NXState":"","Population":1088345,"PrimaryWarningCountyCode":"","PrimaryWarningZoneCode":"","Satellite":"ASIA","Synoptic":"45035","MarineStation":"CIM049","MarineStationGMTOffset":8,"VideoCode":"","PartnerID":null,"Sources":[{"DataType":"CurrentConditions","Source":"AccuWeather","SourceId":1},{"DataType":"DailyForecast","Source":"AccuWeather","SourceId":1},{"DataType":"HourlyForecast","Source":"AccuWeather","SourceId":1},{"DataType":"CurrentConditions","Source":"CMA Public Meteorological Service Center","SourceId":5},{"DataType":"Alerts","Source":"CMA Public Meteorological Service Center","SourceId":5},{"DataType":"DailyForecast","Source":"Huafeng","SourceId":7},{"DataType":"DailyLocalIndices","Source":"China Weather","SourceId":47},{"DataType":"DailyAirQualityForecast","Source":"China Weather","SourceId":47},{"DataType":"AirQuality","Source":"MEP","SourceId":48},{"DataType":"PremiumAirQuality","Source":"MEP","SourceId":48}],"CanonicalPostalCode":"","CanonicalLocationKey":"58194"}
     */

    private int Version;
    private String Key;
    private String Type;
    private int Rank;
    private String LocalizedName;
    private String EnglishName;
    private String PrimaryPostalCode;
    private RegionBean Region;
    private CountryBean Country;
    private AdministrativeAreaBean AdministrativeArea;
    private TimeZoneBean TimeZone;
    private GeoPositionBean GeoPosition;
    private boolean IsAlias;
    private ParentCityBean ParentCity;
    private DetailsBean Details;
    private List<SupplementalAdminAreasBean> SupplementalAdminAreas;
    private List<String> DataSets;

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }

    public String getLocalizedName() {
        return LocalizedName;
    }

    public void setLocalizedName(String LocalizedName) {
        this.LocalizedName = LocalizedName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String EnglishName) {
        this.EnglishName = EnglishName;
    }

    public String getPrimaryPostalCode() {
        return PrimaryPostalCode;
    }

    public void setPrimaryPostalCode(String PrimaryPostalCode) {
        this.PrimaryPostalCode = PrimaryPostalCode;
    }

    public RegionBean getRegion() {
        return Region;
    }

    public void setRegion(RegionBean Region) {
        this.Region = Region;
    }

    public CountryBean getCountry() {
        return Country;
    }

    public void setCountry(CountryBean Country) {
        this.Country = Country;
    }

    public AdministrativeAreaBean getAdministrativeArea() {
        return AdministrativeArea;
    }

    public void setAdministrativeArea(AdministrativeAreaBean AdministrativeArea) {
        this.AdministrativeArea = AdministrativeArea;
    }

    public TimeZoneBean getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(TimeZoneBean TimeZone) {
        this.TimeZone = TimeZone;
    }

    public GeoPositionBean getGeoPosition() {
        return GeoPosition;
    }

    public void setGeoPosition(GeoPositionBean GeoPosition) {
        this.GeoPosition = GeoPosition;
    }

    public boolean isIsAlias() {
        return IsAlias;
    }

    public void setIsAlias(boolean IsAlias) {
        this.IsAlias = IsAlias;
    }

    public ParentCityBean getParentCity() {
        return ParentCity;
    }

    public void setParentCity(ParentCityBean ParentCity) {
        this.ParentCity = ParentCity;
    }

    public DetailsBean getDetails() {
        return Details;
    }

    public void setDetails(DetailsBean Details) {
        this.Details = Details;
    }

    public List<SupplementalAdminAreasBean> getSupplementalAdminAreas() {
        return SupplementalAdminAreas;
    }

    public void setSupplementalAdminAreas(List<SupplementalAdminAreasBean> SupplementalAdminAreas) {
        this.SupplementalAdminAreas = SupplementalAdminAreas;
    }

    public List<String> getDataSets() {
        return DataSets;
    }

    public void setDataSets(List<String> DataSets) {
        this.DataSets = DataSets;
    }

    public static class RegionBean {
        /**
         * ID : ASI
         * LocalizedName : 亚洲
         * EnglishName : Asia
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }

    public static class CountryBean {
        /**
         * ID : CN
         * LocalizedName : 中国
         * EnglishName : China
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }

    public static class AdministrativeAreaBean {
        /**
         * ID : 44
         * LocalizedName : 广东省
         * EnglishName : Guangdong
         * Level : 1
         * LocalizedType : 省
         * EnglishType : Province
         * CountryID : CN
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;
        private int Level;
        private String LocalizedType;
        private String EnglishType;
        private String CountryID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public String getLocalizedType() {
            return LocalizedType;
        }

        public void setLocalizedType(String LocalizedType) {
            this.LocalizedType = LocalizedType;
        }

        public String getEnglishType() {
            return EnglishType;
        }

        public void setEnglishType(String EnglishType) {
            this.EnglishType = EnglishType;
        }

        public String getCountryID() {
            return CountryID;
        }

        public void setCountryID(String CountryID) {
            this.CountryID = CountryID;
        }
    }

    public static class TimeZoneBean {
        /**
         * Code : CST
         * Name : Asia/Shanghai
         * GmtOffset : 8.0
         * IsDaylightSaving : false
         * NextOffsetChange : null
         */

        private String Code;
        private String Name;
        private double GmtOffset;
        private boolean IsDaylightSaving;
        private Object NextOffsetChange;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public double getGmtOffset() {
            return GmtOffset;
        }

        public void setGmtOffset(double GmtOffset) {
            this.GmtOffset = GmtOffset;
        }

        public boolean isIsDaylightSaving() {
            return IsDaylightSaving;
        }

        public void setIsDaylightSaving(boolean IsDaylightSaving) {
            this.IsDaylightSaving = IsDaylightSaving;
        }

        public Object getNextOffsetChange() {
            return NextOffsetChange;
        }

        public void setNextOffsetChange(Object NextOffsetChange) {
            this.NextOffsetChange = NextOffsetChange;
        }
    }

    public static class GeoPositionBean {
        /**
         * Latitude : 22.536
         * Longitude : 113.927
         * Elevation : {"Metric":{"Value":30,"Unit":"m","UnitType":5},"Imperial":{"Value":98,"Unit":"ft","UnitType":0}}
         */

        private double Latitude;
        private double Longitude;
        private ElevationBean Elevation;

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public ElevationBean getElevation() {
            return Elevation;
        }

        public void setElevation(ElevationBean Elevation) {
            this.Elevation = Elevation;
        }

        public static class ElevationBean {
            /**
             * Metric : {"Value":30,"Unit":"m","UnitType":5}
             * Imperial : {"Value":98,"Unit":"ft","UnitType":0}
             */

            private MetricBean Metric;
            private ImperialBean Imperial;

            public MetricBean getMetric() {
                return Metric;
            }

            public void setMetric(MetricBean Metric) {
                this.Metric = Metric;
            }

            public ImperialBean getImperial() {
                return Imperial;
            }

            public void setImperial(ImperialBean Imperial) {
                this.Imperial = Imperial;
            }

            public static class MetricBean {
                /**
                 * Value : 30.0
                 * Unit : m
                 * UnitType : 5
                 */

                private double Value;
                private String Unit;
                private int UnitType;

                public double getValue() {
                    return Value;
                }

                public void setValue(double Value) {
                    this.Value = Value;
                }

                public String getUnit() {
                    return Unit;
                }

                public void setUnit(String Unit) {
                    this.Unit = Unit;
                }

                public int getUnitType() {
                    return UnitType;
                }

                public void setUnitType(int UnitType) {
                    this.UnitType = UnitType;
                }
            }

            public static class ImperialBean {
                /**
                 * Value : 98.0
                 * Unit : ft
                 * UnitType : 0
                 */

                private double Value;
                private String Unit;
                private int UnitType;

                public double getValue() {
                    return Value;
                }

                public void setValue(double Value) {
                    this.Value = Value;
                }

                public String getUnit() {
                    return Unit;
                }

                public void setUnit(String Unit) {
                    this.Unit = Unit;
                }

                public int getUnitType() {
                    return UnitType;
                }

                public void setUnitType(int UnitType) {
                    this.UnitType = UnitType;
                }
            }
        }
    }

    public static class ParentCityBean {
        /**
         * Key : 58194
         * LocalizedName : 深圳
         * EnglishName : Shenzhen
         */

        private String Key;
        private String LocalizedName;
        private String EnglishName;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }

    public static class DetailsBean {
        /**
         * Key : 2332635
         * StationCode : CI3221
         * StationGmtOffset : 8.0
         * BandMap : CIMO
         * Climo : ZB70
         * LocalRadar :
         * MediaRegion : null
         * Metar : ZGSZ
         * NXMetro :
         * NXState :
         * Population : 1088345
         * PrimaryWarningCountyCode :
         * PrimaryWarningZoneCode :
         * Satellite : ASIA
         * Synoptic : 45035
         * MarineStation : CIM049
         * MarineStationGMTOffset : 8.0
         * VideoCode :
         * PartnerID : null
         * Sources : [{"DataType":"CurrentConditions","Source":"AccuWeather","SourceId":1},{"DataType":"DailyForecast","Source":"AccuWeather","SourceId":1},{"DataType":"HourlyForecast","Source":"AccuWeather","SourceId":1},{"DataType":"CurrentConditions","Source":"CMA Public Meteorological Service Center","SourceId":5},{"DataType":"Alerts","Source":"CMA Public Meteorological Service Center","SourceId":5},{"DataType":"DailyForecast","Source":"Huafeng","SourceId":7},{"DataType":"DailyLocalIndices","Source":"China Weather","SourceId":47},{"DataType":"DailyAirQualityForecast","Source":"China Weather","SourceId":47},{"DataType":"AirQuality","Source":"MEP","SourceId":48},{"DataType":"PremiumAirQuality","Source":"MEP","SourceId":48}]
         * CanonicalPostalCode :
         * CanonicalLocationKey : 58194
         */

        private String Key;
        private String StationCode;
        private double StationGmtOffset;
        private String BandMap;
        private String Climo;
        private String LocalRadar;
        private Object MediaRegion;
        private String Metar;
        private String NXMetro;
        private String NXState;
        private int Population;
        private String PrimaryWarningCountyCode;
        private String PrimaryWarningZoneCode;
        private String Satellite;
        private String Synoptic;
        private String MarineStation;
        private double MarineStationGMTOffset;
        private String VideoCode;
        private Object PartnerID;
        private String CanonicalPostalCode;
        private String CanonicalLocationKey;
        private List<SourcesBean> Sources;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getStationCode() {
            return StationCode;
        }

        public void setStationCode(String StationCode) {
            this.StationCode = StationCode;
        }

        public double getStationGmtOffset() {
            return StationGmtOffset;
        }

        public void setStationGmtOffset(double StationGmtOffset) {
            this.StationGmtOffset = StationGmtOffset;
        }

        public String getBandMap() {
            return BandMap;
        }

        public void setBandMap(String BandMap) {
            this.BandMap = BandMap;
        }

        public String getClimo() {
            return Climo;
        }

        public void setClimo(String Climo) {
            this.Climo = Climo;
        }

        public String getLocalRadar() {
            return LocalRadar;
        }

        public void setLocalRadar(String LocalRadar) {
            this.LocalRadar = LocalRadar;
        }

        public Object getMediaRegion() {
            return MediaRegion;
        }

        public void setMediaRegion(Object MediaRegion) {
            this.MediaRegion = MediaRegion;
        }

        public String getMetar() {
            return Metar;
        }

        public void setMetar(String Metar) {
            this.Metar = Metar;
        }

        public String getNXMetro() {
            return NXMetro;
        }

        public void setNXMetro(String NXMetro) {
            this.NXMetro = NXMetro;
        }

        public String getNXState() {
            return NXState;
        }

        public void setNXState(String NXState) {
            this.NXState = NXState;
        }

        public int getPopulation() {
            return Population;
        }

        public void setPopulation(int Population) {
            this.Population = Population;
        }

        public String getPrimaryWarningCountyCode() {
            return PrimaryWarningCountyCode;
        }

        public void setPrimaryWarningCountyCode(String PrimaryWarningCountyCode) {
            this.PrimaryWarningCountyCode = PrimaryWarningCountyCode;
        }

        public String getPrimaryWarningZoneCode() {
            return PrimaryWarningZoneCode;
        }

        public void setPrimaryWarningZoneCode(String PrimaryWarningZoneCode) {
            this.PrimaryWarningZoneCode = PrimaryWarningZoneCode;
        }

        public String getSatellite() {
            return Satellite;
        }

        public void setSatellite(String Satellite) {
            this.Satellite = Satellite;
        }

        public String getSynoptic() {
            return Synoptic;
        }

        public void setSynoptic(String Synoptic) {
            this.Synoptic = Synoptic;
        }

        public String getMarineStation() {
            return MarineStation;
        }

        public void setMarineStation(String MarineStation) {
            this.MarineStation = MarineStation;
        }

        public double getMarineStationGMTOffset() {
            return MarineStationGMTOffset;
        }

        public void setMarineStationGMTOffset(double MarineStationGMTOffset) {
            this.MarineStationGMTOffset = MarineStationGMTOffset;
        }

        public String getVideoCode() {
            return VideoCode;
        }

        public void setVideoCode(String VideoCode) {
            this.VideoCode = VideoCode;
        }

        public Object getPartnerID() {
            return PartnerID;
        }

        public void setPartnerID(Object PartnerID) {
            this.PartnerID = PartnerID;
        }

        public String getCanonicalPostalCode() {
            return CanonicalPostalCode;
        }

        public void setCanonicalPostalCode(String CanonicalPostalCode) {
            this.CanonicalPostalCode = CanonicalPostalCode;
        }

        public String getCanonicalLocationKey() {
            return CanonicalLocationKey;
        }

        public void setCanonicalLocationKey(String CanonicalLocationKey) {
            this.CanonicalLocationKey = CanonicalLocationKey;
        }

        public List<SourcesBean> getSources() {
            return Sources;
        }

        public void setSources(List<SourcesBean> Sources) {
            this.Sources = Sources;
        }

        public static class SourcesBean {
            /**
             * DataType : CurrentConditions
             * Source : AccuWeather
             * SourceId : 1
             */

            private String DataType;
            private String Source;
            private int SourceId;

            public String getDataType() {
                return DataType;
            }

            public void setDataType(String DataType) {
                this.DataType = DataType;
            }

            public String getSource() {
                return Source;
            }

            public void setSource(String Source) {
                this.Source = Source;
            }

            public int getSourceId() {
                return SourceId;
            }

            public void setSourceId(int SourceId) {
                this.SourceId = SourceId;
            }
        }
    }

    public static class SupplementalAdminAreasBean {
        /**
         * Level : 2
         * LocalizedName : 深圳市
         * EnglishName : Shenzhen
         */

        private int Level;
        private String LocalizedName;
        private String EnglishName;

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }
}
