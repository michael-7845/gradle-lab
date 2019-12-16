package groovy.tool.script

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */

Map tax_address = [
        NY: [line1: 'PO 123', street: 'East Broadway', streetNumber: '42', zip: '10002', city: 'New York', state: 'NY', country: 'US'],
        MA: [line1: 'PO 84', street: 'Boston University, One Silber Way', streetNumber: '81', zip: '02215', city: 'Boston', state: 'MA', country: 'US'],
        MA2: [street: 'Boylston St', streetNumber: '815', zip: '02116', city: 'Boston', state: 'MA', country: 'US'],
        NJ: [street: 'Montgomery St', streetNumber: '561', zip: '07302', city: 'Jersey City', state: 'NJ', country: 'US'],
        CA: [street: 'W Hillside Ave', streetNumber: '81', zip: '94960', city: 'San Anselmo', state: 'CA', country: 'US'],
        HI: [street: 'Nelsontown Rd', streetNumber: '2793', zip: '96797', city: 'Waipahu', state: 'HI', country: 'US'],
        DC: [street: 'S Dakota Ave NE', streetNumber: '3101', zip: '20018', city: 'Washington', state: 'DC', country: 'US'],
        OR: [street: 'S Rhoten Rd', streetNumber: '25581', zip: '97002', city: 'Aurora', state: 'OR', country: 'US'],
        AK: [street: 'Morningtide Ct', streetNumber: '1717', zip: '99501', city: 'Anchorage', state: 'AK', country: 'US'],
        DE: [street: 'Hastings Rd', streetNumber: '20275', zip: '19945', city: 'Frankford', state: 'DE', country: 'US'],
        MT: [street: 'Wells St', streetNumber: '610', zip: '59301', city: 'Miles City', state: 'MT', country: 'US'],
        NH: [street: 'Franklin St', streetNumber: '13', zip: '03301', city: 'Concord', state: 'NH', country: 'US'],
        FL: [street: 'S. Magnolia St.', streetNumber: '514', zip: '32806', city: 'Orlando', state: 'FL', country: 'US'],
        FL2: [street: 'Robin St', streetNumber: '1514', zip: '33823', city: 'Auburndale', state: 'FL', country: 'US'],
        GA: [street: 'Warm Spri #LOCATED AT', streetNumber: '6990', zip: '31820', city: 'Midland', state: 'GA', country: 'US'],
        CO: [street: 'County 76 Rd #70', streetNumber: '22110', zip: '80615', city: 'Eaton', state: 'CO', country: 'US'],
        IL: [street: 'N 2150 East Rd', streetNumber: '26221', zip: '61814', city: 'Bismarck', state: 'IL', country: 'US'],
        MO: [street: 'Grand Cove Dr', streetNumber: '1343', zip: '65079', city: 'Sunrise Beach', state: 'MO', country: 'US'],
        SC: [street: 'Will Richardson Cir', streetNumber: '210', zip: '29063', city: 'Irmo', state: 'SC', country: 'US'],
        OH: [street: 'W Towne Ln', streetNumber: '1325', zip: '43015', city: 'Delaware', state: 'OH', country: 'US'],
        PA: [street: '#252 W', streetNumber: '23', zip: '18901', city: 'Doylestown', state: 'PA', country: 'US'],
        MN: [street: 'Woodland Ave', streetNumber: '220', zip: '56031', city: 'Fairmont', state: 'MN', country: 'US'],
        TX: [street: 'N 11th St', streetNumber: '1003', zip: '76707', city: 'Waco', state: 'TX', country: 'US'],
        IN: [street: 'N 550th W #500 W', streetNumber: '11332', zip: '46148', city: 'Knightstown', state: 'IN', country: 'US'],
        MI: [street: 'Dwyer St', streetNumber: '17633', zip: '48212', city: 'Hamtramck', state: 'MI', country: 'US'],
]

//        {
//            "shippingAddress": {
//            "addressLine1": "53 State St",
//            "addressLine2": "16th Floor",
//            "city": "Los Angeles",
//            "state": "CA",
//            "zip": "90099",
//            "country": "US"
//        },

Map result = [:]
tax_address.each { key, value ->
//    println "key: ${key}, value: ${value}"
    def _map = [:]
    _map = value.subMap(['city', 'state', 'zip', 'country', ])
    _map.addressLine1 = value.street
    _map.addressLine2 = value.streetNumber
    result[(key)] = _map
}
println result
