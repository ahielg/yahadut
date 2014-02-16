package hebDate;import java.io.File;import java.io.FileInputStream;import java.util.*;/** * A list of city locales. This list extends TreeMap so lookup can be done by key. (A key in * this case is a unique string which should be a short form version of the locale name). * <p/> * This class can also use a properties file of which each property will be loaded * into a CityLocale object and added to the list.<BR> * The properties in the property file must have the form: * <P><CODE> * Key= Name: latitude: longitude: time zone id<BR> * e.g. <BR> * Toronto=Toronto, Ontario:43.65:-79.38:EST * </CODE><P> * * @see CityLocale */@SuppressWarnings("UnusedDeclaration")public class CityLocaleList extends TreeMap {    public CityLocaleList() {        super();    }    public CityLocaleList(String file) {        super();        setProperties(file);    }    /**     * Load properties from a property file that contains city locale info.     * The form of the property should be:<BR>     * Key= Name: latitude: longitude: time zone id<BR>     * e.g. <BR>     * Toronto=Toronto, Ontario:43.65:-79.38:EST     */    public void setProperties(String filename) {        Properties properties = new Properties();        // load properties from filename        try {            File file = new File(filename);            if (!file.exists())                throw new IllegalArgumentException("Property file " + filename + " does not exist. Path was: " + file.getCanonicalPath());            //System.out.println("Property File path: " +file.getCanonicalPath());            FileInputStream fis = new FileInputStream(file);            properties.load(fis);        } catch (Exception e) {            e.printStackTrace();            return;        }        Enumeration myEnum = properties.propertyNames();        // loop through each of the properties and palce them into a CityLocale object        while (myEnum.hasMoreElements()) {            String key = (String) myEnum.nextElement();            //System.out.println("Loading " + key);            String value = properties.getProperty(key);            StringTokenizer tokenizer = new StringTokenizer(value, ":");            try {                String name = tokenizer.nextToken();                double latitude = Double.valueOf(tokenizer.nextToken());                double longitude = Double.valueOf(tokenizer.nextToken());                String zoneID = tokenizer.nextToken();                addCityLocale(new CityLocale(key, name, latitude, longitude, TimeZone.getTimeZone(zoneID)));            }            // handle better            catch (Exception e) {                e.printStackTrace();            }        }    }    /**     * Adds a city locale to the list of city locales. If this locale's key     * matches a key already in the list, it overrides it     */    public void addCityLocale(CityLocale locale) {        put(locale.getKey(), locale);    }    /**     * Gets a CityLocale from the list based on its key. Returns null if     * the CityLocale was not in the list.     *     * @param key     * @return The CityLocale object that matches this key or null if it is not     *         in the list.     */    public CityLocale getCityLocale(String key) {        return (CityLocale) get(key);    }    /**     * Gets an array of the keys in ordered form.     *     * @return The keys as a String array.     */    public String[] getCityLocaleKeys() {        Set keySet = keySet();        Iterator it = keySet.iterator();        String[] keyList = new String[keySet.size()];        for (int i = 0; it.hasNext(); i++)            keyList[i] = (String) it.next();        return keyList;    }    public CityLocale getCityLocale(Cities city) {        return getCityLocale(city.name());    }}