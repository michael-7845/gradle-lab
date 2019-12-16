package groovy.tool.script

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */

//Map input = [NJ: '561 Montgomery St, Jersey City, NJ, 07302',
//             CA: '81 W Hillside Ave, San Anselmo, CA, 94960',
//             HI: '2793 Nelsontown Rd, Waipahu, HI, 96797',
//             DC: '3101 S Dakota Ave NE, Washington, DC, 20018',
//             MA: '815 Boylston St, Boston, MA, 02116',
//             OR: '25581 S Rhoten Rd, Aurora, OR, 97002',
//             AK: '1717 Morningtide Ct, Anchorage, AK, 99501',
//             DE: '20275 Hastings Rd, Frankford, DE, 19945',
//             MT: '610 Wells St, Miles City, MT, 59301',
//             NH: '13 Franklin St, Concord, NH, 03301', ]

Map input = [ NJ: '561 Montgomery St, Jersey City, NJ, 07302',
              CA: '81 W Hillside Ave, San Anselmo, CA, 94960',
              HI: '2793 Nelsontown Rd, Waipahu, HI, 96797',
              DC: '3101 S Dakota Ave NE, Washington, DC, 20018',
              MA: '815 Boylston St, Boston, MA, 02116',
              OR: '25581 S Rhoten Rd, Aurora, OR, 97002',
              AK: '1717 Morningtide Ct, Anchorage, AK, 99501',
              DE: '20275 Hastings Rd, Frankford, DE, 19945',
              MT: '610 Wells St, Miles City, MT, 59301',
              NH: '13 Franklin St, Concord, NH, 03301',
              FL: '2793 Nelsontown Rd, Jay, FL, 32565',
              GA: '6990 Warm Spri #LOCATED AT, Midland, GA, 31820',
              FL: '2793 Nelsontown Rd, Jay, FL, 32565',
              CO: '22110 County 76 Rd #70, Eaton, CO, 80615',
              IL: '26221 N 2150 East Rd, Bismarck, IL, 61814',
              MO: '1343 Grand Cove Dr, Sunrise Beach, MO, 65079',
              FL: '1514 Robin St, Auburndale, FL, 33823',
              SC: '210 Will Richardson Cir, Irmo, SC, 29063',
              OH: '1325 W Towne Ln, Delaware, OH, 43015',
              PA: 'Bailiwick 23 #252 W, Doylestown, PA, 18901',
              MN: '220 Woodland Ave, Fairmont, MN, 56031',
              TX: '1003 N 11th St, Waco, TX, 76707',
              IN: '11332 N 550th W #500 W, Knightstown, IN, 46148',
              MI: '17633 Dwyer St, Hamtramck, MI, 48212', ]

def addr_matcher = ~/(\d+) (.+), (.+), (\S{2}), (\d{5})/
//def addr_m = '561 Montgomery St, Jersey City, NJ, 07302' =~ addr_matcher
//if(addr_m.find()) {
//    println addr_m[0][1]
//    println addr_m[0][2]
//    println addr_m[0][3]
//    println addr_m[0][4]
//    println addr_m[0][5]
//}
input.each { state, addr_str ->
    def addr_m = addr_str =~ addr_matcher
    if(addr_m.find()) {
        println "${state}: [street: '${addr_m[0][2]}', streetNumber: '${addr_m[0][1]}', zip: '${addr_m[0][5]}', city: '${addr_m[0][3]}', state: '${addr_m[0][4]}', country: 'US'], "
    }
}