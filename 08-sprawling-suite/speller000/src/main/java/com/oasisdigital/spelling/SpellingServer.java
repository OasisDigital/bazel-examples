package com.oasisdigital.spelling;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.grpc.*;
import com.oasisdigital.spelling.api.*;

import java.io.IOException;

public class SpellingServer
    extends SpellCheckerGrpc.SpellCheckerImplBase {
  Set<String> dictionary = new HashSet<String>();
  static int port = 8600 + 0;

  SpellingServer() {
    String words = new String("aa|abba|abductions|abnormal|abort|abrupt|absolutes|abstract|abusing|accelerated|accepts|accidents|accompany|accordance|accounting|accusation|aced|aching|acne|acquisition|acting|actively|actuality|adapt|addictive|addresses|adjacent|adjustments|admirably|admissions|ado|adorable|adultery|advantages|adversity|advise|aerobic|affairs|affidavits|afforded|afterlife|against|agents|agile|agonizing|aground|aided|aimed|airhead|airport|aisles|alarming|albums|ale|algebra|alienation|allegations|alleviate|allowable|almighty|aloud|alt|alternating|aluminum|amateurs|ambience|ambulance|amenities|ammonia|amounts|amulet|anaesthetic|analyzing|anchorage|anecdotes|angel|angora|animated|annihilated|annoy|annulled|anorexic|antagonize|anthropologists|anticipation|antiquing|anxious|anyway|apes|apologise|appalling|appear|appendectomy|applauding|applications|appraisal|apprehensive|approval|apropos|arbitration|archeological|are|argues|arise|armor|arouse|array|arrived|arsenal|artichoke|artiste|asbestos|aside|aspects|assassin|assembler|assessment|assist|association|assurance|astonished|astronomical|athletes|atrocities|attack|attempting|attentions|attracted|auctioneer|auditing|august|auspicious|authorize|autographs|autonomy|avatars|aversion|await|awards|awkward|axle|babies|bachelors|backfiring|backseat|backwards|badgering|bagels|bail|bake|balanced|balk|ballistics|ballroom|ban|bands|banished|bankrupt|banshee|barbarian|barbs|bargained|barkeep|barnyard|barred|barrio|baseball|bashing|basketballs|bastille|bathroom|batteries|batty|bays|beak|bear|beast|beau|beck|bedpan|bee|beeper|beets|beggars|begun|behold|belief|belive|bellyaching|belted|bender|benefit|benny|berlin|besieged|bethesda|betting|biased|bicycle|bigamist|bigotry|bilge|billionaires|binding|biographies|bipartisan|birthday|bishops|bitten|blackberry|blackness|blam|blankly|blatant|bleached|blemish|blew|blindly|bliss|bloc|blokes|bloodline|blooms|blowed|blubber|blues|blur|bo|boardwalk|bobbing|bogeyman|boilers|bolted|bombs|boned|boning|boobs|booker|bookshelf|boost|bootleg|border|borrowed|bot|bottled|boulevard|bounty|bouts|bowler|boxers|bozo|brad|brain|brakes|brash|braved|breach|breaking|breasts|breathtaking|bren|bribe|bridegroom|briefed|brighter|bringing|britt|broccoli|brokerage|brook|brotherhood|brownies|bruises|brushing|buckaroo|bud|buff|bugger|builds|bulky|bullheaded|bummed|bumpy|bungled|bunt|burgeoning|buries|burns|burton|busier|bust|busybody|butted|butting|buying|bwana|bystanders|cabins|cacophony|cafeteria|cake|calculations|calico|calmer|camcorder|campaigning|campuses|cancel|candidacy|candor|cannibals|cant|capable|capitol|capsule|capture|carbohydrates|cardigan|career|caretaker|carney|carousel|carried|carted|cartoonist|carving|cash|casitas|castles|catalog|catcher|caterer|cathartic|caught|cautiously|caviar|ceiling|celibacy|cells|census|centimeters|cerebral|certifiably|chad|chairman|challenging|championship|change|chants|chaplain|characterizing|charging|charlies|charted|chasm|chauffeur|cheating|checkout|cheer|cheese|chemical|cherries|chevalier|chicano|chief|childlike|chime|chino|chirping|chloroformed|choked|choose|choppy|chosen|chromosomes|chucks|chunky|cigarette|circled|circulation|cissy|citrus|civilization|clairvoyant|clan|clarification|classic|classroom|claws|cleanliness|clearances|clemency|cleverness|cliff|clincher|clink|clitoris|clods|closed|closure|clotted|cloves|clue|clunker|coaches|coasting|cobra|cockney|coconuts|coding|coffers|coil|coke|colds|collaborating|collarbone|collective|collie|colonies|coloring|columns|combines|comedian|comfort|comics|commando|commentaries|commercials|commitment|common|communicating|communities|companions|comparison|compelling|competitions|complained|completion|complication|composers|comprehensive|compromising|comrades|conceivable|concept|concessions|conclusive|concussion|condition|condoms|conductor|conference|confessor|confidentiality|confirmation|conflicts|confronts|congratulated|conjugate|connected|connoisseur|consciously|consequences|consideration|console|conspiracy|constipation|constructed|consulted|consummated|containers|contemplating|contest|continually|continuum|contractor|contribute|control|convened|conversation|converting|convincing|cookie|cooling|cooperating|cooties|copper|cor|coriander|cornered|coronary|corpse|correctly|corroborate|cos|costa|cottage|council|countdown|counterproductive|county|courier|courting|cove|coverup|cowboys|coyotes|cracked|crafted|cramped|cranks|crashed|crave|craze|creamer|creation|credenza|creep|crepes|crib|crimes|cripple|criterion|critters|cronies|crossbow|crouched|crowed|crucifix|cruise|crumbling|crusader|crutch|crystal|cubs|cued|culpa|cultured|cupboards|curb|curiosity|currency|cursive|cushion|custom|cuteness|cutoff|cyberspace|cynic|dabble|dag|dairy|damage|damp|dances|dangers|dares|darling|dasher|dates|dawn|daytime|deader|dealer|dearie|debate|debris|decaf|deceive|decibels|deck|declining|decor|decoupage|dedication|deeds|defaced|defects|defenses|deficit|definitive|deft|degrade|deke|delete|delicates|delirium|deltas|delving|demise|demolitions|demonstrators|denominations|dents|departments|depict|deport|deposits|deprivation|derailing|derrick|describes|deserting|designed|desk|despise|destinations|destruct|detailing|detectives|determined|detonating|deuces|development|devilishly|devour|diabetic|diagram|diamonds|diazepam|dicky|dictionary|dieting|difficulties|digger|dignity|diligent|dimensional|dimpled|dingo|dinning|dipping|directly|disabilities|disagreements|disappointed|disarm|disc|disciplining|discontent|discourse|discreetly|discus|diseases|disgusted|dishwasher|disintegrated|dismantled|dismount|disorienting|dispensation|display|disposition|disrespect|dissatisfied|dissipate|distant|distinguish|distraction|distributor|disturbing|diuretic|divert|divining|divulged|dock|documentaries|dodgy|doghouse|doll|dolt|dominant|donated|donor|doomsday|doorway|dorky|dose|dotted|doubts|dowdy|downpour|downtrodden|drab|dragonfly|drama|draw|dreaded|dreamt|dressed|dried|drilling|drips|droll|dropout|drown|drugstore|drunken|dub|ducky|dues|dulcet|dumdum|dumps|duped|dust|dwarfs|dyed|dysfunction|earlier|earning|earthlings|easiest|eaten|ebony|economical|eczema|edible|edits|eerie|effeminate|eggnog|egotistical|eighty|elastic|electing|electricity|electronics|elephants|elicit|elk|eloquent|emanates|embarrassed|embezzle|embrace|emerged|emit|emphasize|employer|emptiness|enact|enclosed|encouraging|endangerment|endless|endure|enforcement|engine|engrossing|enjoyed|enlisting|enriched|ensue|entering|enthralled|entities|entrances|entwined|envoy|epiphany|equation|er|erection|errand|erupt|escapee|espionage|established|estimated|eternal|ethnic|euphoric|evaluated|even|eventually|everywhere|eviscerate|ex|exam|exasperating|excels|excessively|exclamation|excursion|execution|exercise|exhausting|exhilarating|existed|exits|expanded|expects|expenditures|experimentation|explain|exploded|explored|exporter|expresses|extend|exterminated|extort|extracts|extras|extremities|eyelash|fa|faced|facilitated|factors|fading|fainted|fairness|faking|fallow|familiarize|fanaticism|fans|far|farmers|fascinate|fast|fatality|fatigue|faults|favorite|fay|feasting|feces|feedback|fees|fellas|felons|femur|ferment|fervent|fetal|feverish|fibbing|fiddle|fieldstone|fifteenth|fighting|figuring|fillets|filmmaking|final|finance|finding|finger|finishes|firebug|firemen|firing|fish|fist|fitter|fixating|fizzle|flair|flaming|flapping|flashes|flatter|flavor|flay|fleece|flexible|flies|flip|float|flood|floozy|florist|flower|fluffing|flunky|flux|fob|fog|folding|follower|fondling|foolishness|footman|for|forced|foreclosure|forensics|forests|forged|forgive|forklift|formed|fornicating|forthwith|fortune|fossilized|founder|fourth|fractions|frame|frank|fraternity|freak|freed|freely|freezing|freshen|fret|friends|frightens|fritters|frontal|frown|fruity|fudged|fuhrer|fully|functioning|fundraising|funnies|furrowed|fused|future|gag|gainful|gall|gallows|gamer|gangland|gar|gardening|garment|gasbag|gassy|gathered|gauze|gazebo|ged|geishas|gender|generation|genetic|genoa|genuine|geology|german|gets|ghoul|giddyup|gigolo|gimmie|girlfriend|given|glad|glance|glassware|glengarry|glitches|glop|gloves|gluttony|goad|gobbledegook|goddesses|goers|goldfish|gonorrhea|goodly|goofy|gor|gossips|gout|grab|gracing|gradually|grain|grandchild|grandkid|grange|grapefruit|grass|gratuitous|gravy|greater|greens|greyhound|griffin|grimes|grip|groaning|grooming|grosser|groundhog|groups|grown|grudges|guarantee|guardrail|guessing|guiding|guitars|gun|gunpowder|gushy|guzzling|habits|hades|hailing|haired|halfback|hallucinate|halter|hammers|handball|handicap|handout|handwriting|hangout|hansom|happiness|harbour|hardship|harmful|harpies|harshly|haste|hatchet|hatter|haute|hay|haze|headboard|headline|heads|healing|hear|heartbeats|hearts|heathens|heaviest|heckling|heft|heinie|helicopters|hello|helper|hemline|hence|herb|hereditary|heroic|hesitate|hey|hick|hideout|highlands|highways|hill|hind|hippie|his|histories|hits|hoarding|hobby|hoes|hold|holes|holler|holt|homegrown|homes|homing|honestly|honks|honouring|hooker|hoops|hoped|hopping|hormone|horrendous|horseback|hose|host|hosting|hotheaded|hounds|houseguests|housing|howling|huckster|hugged|hullo|humble|humiliate|hummus|humping|hundreds|hunky|hurley|hurt|huskies|hybrid|hyenas|hyperbole|hypo|hypothetical|ice|icicles|idealist|identifies|idiotic|iff|ignorant|illegal|illuminate|image|imagines|imitation|immersion|immortalized|impale|impediment|imperialist|implanted|implications|importance|imposition|impractical|imprint|improvements|impulsive|inadequate|incantations|incense|incidence|inclined|incomparable|inconsolable|increased|incubation|indefinite|indicate|indicted|indignation|individuality|indulge|inedible|inexperienced|infatuated|inferior|infiltrate|inflammatory|influence|informal|infrared|ingles|inhaler|inhuman|initiatives|injury|inner|innovation|inquisitor|insects|inside|insinuated|insolence|inspiration|installment|instigator|institutionalized|instruments|insurance|integrated|intend|intentional|intercept|interface|intermediary|internist|interpretive|interruption|intervention|intimated|intoxication|introduces|intruding|invalid|inventory|investigative|invisibility|invoke|iris|irrational|irrevocably|islanders|it|ivories|jackals|jade|jailed|jams|jasmine|jay|jeeps|jeopardized|jerry|jets|jezebel|jill|jitters|jockstrap|join|jokers|jot|journeys|judged|jug|juicy|jumper|junior|juror|justify|kangaroo|kat|keeping|keno|keyboard|kibble|kicky|kidnappers|killers|kilt|kindly|kins|kisses|kitty|kneel|knights|knocked|knotted|knuckles|kris|laboratories|laced|lackluster|laddies|lagoon|lam|lamp|landfill|landscapes|lanky|lapses|largest|laser|lasted|latex|laughable|launching|lavish|laws|layaway|lead|league|leans|learns|leaves|ledger|leftovers|legend|legislature|lemon|lens|leprechaun|lest|lettuce|levitation|liaison|liberation|licensed|lid|lieu|lifer|lifts|lightheaded|likelihood|lima|limey|limousines|linen|lingers|lion|liquidate|listened|liter|litigious|liven|lo|loans|lobe|locals|locked|locust|logan|loins|loner|longtime|loom|loops|looting|lore|lot|loudest|lovable|lovely|lowe|lows|lubrication|lucrative|lumbar|lunacy|lung|lurk|luv|lynching|mace|mack|madly|mafia|magician|magnify|maids|maimed|maintains|makeover|male|malign|mamie|manages|maneuvered|manhattan|manicures|manipulated|mannequin|mansions|manufactured|mapped|marched|margins|marines|markets|marquis|marshal|martins|mascot|mason|massaged|master|masturbation|mate|matey|matrimony|mattresses|maverick|maya|mayor|meals|meanness|measures|mechanic|meddlesome|medicate|mediocrity|meetings|melodramatic|members|memorial|menage|mensa|menu|mercilessly|merit|merry|messed|metal|meteorite|meticulous|mick|microwave|midlife|miffed|mikes|militants|millennium|millions|mina|mine|miniature|minister|minors|miracle|miscalculated|miscreant|misguided|mislead|misreading|missions|mistakes|misunderstand|mitts|mm|mobs|model|modest|moil|molded|molest|molten|momma|monetary|monitor|monogamy|monsters|moo|moonlighting|mopey|morally|morning|morse|mortified|mostly|mothers|motivations|motors|mountainside|mousse|mouthy|mow|muck|muffled|mulberry|multimedia|mum|munching|murderers|muscles|music|muskrat|mutants|mutual|mysteriously|mythological|nah|named|nancy|napoleon|narcotics|nasal|nativity|nauseating|navy|neat|necklaces|needing|negative|negligent|neighborhood|nelson|nerves|networks|neutered|newborn|newscast|next|nick|nieces|nightlife|nihilist|ninny|nite|noble|nods|nomadic|nominees|nonviolent|norm|northwestern|nostalgia|notch|noticeable|notions|nouveau|noxious|nudge|numb|nuptial|nutcase|nuttier|oar|obeying|objective|oblique|obscure|observer|obsolete|obvious|occupation|occurs|oddly|offbeat|offer|officially|ogle|oily|oldest|omelette|oncology|onto|open|operas|operator|opportunity|oppressive|options|orator|orchestrate|ordinance|organisation|organizing|orienteering|ornament|orthodontist|otherwise|ours|outcasts|outfield|outlander|outlines|outraged|outsiders|oval|overanxious|overcompensating|overdue|overheard|overlooked|overqualified|overseas|overstayed|overview|ow|owners|oyez|paces|packer|paddles|pageant|pained|painter|pajamas|palm|pam|pancake|panic|pantheon|paparazzi|par|paradise|paralyze|paranormal|pardon|paris|parkway|parrots|participated|parting|parts|passed|passive|pasties|patched|paternity|paths|patriotic|patronizing|pause|pawning|paying|peace|peaks|pebble|pecs|pedestrians|peed|peep|peg|pemmican|pendant|penis|pension|pepper|percentile|perfecting|performers|perimeters|perjure|permission|perpetual|persnickety|personals|persuasive|perverts|pesto|petitioner|pewter|phase|pheromones|philosophy|phonies|photographer|physical|physique|picket|pickup|piddling|piercing|piglet|pilgrim|pills|pina|pineapple|pinks|pinto|piqued|pissing|pitcher|pittance|pixilated|placing|plaintiff|planing|plantations|plateau|platonic|playboys|playoffs|pleaded|pleaser|pledging|plow|plugging|plums|poach|pod|poets|points|poked|police|polite|pollard|poltergeist|poncho|poodle|poop|poppa|popular|poring|port|portrait|poses|posse|possibility|poster|postponed|potency|potsie|pounder|pow|powers|practising|pralines|prayed|preachy|precinct|predators|predisposed|preferences|prejudicial|premiere|preparation|prerequisite|present|preserved|presiding|prestige|pretending|pretzel|preventive|priced|priest|primates|princes|printers|prisoner|privileges|probate|procedures|procession|procure|produces|profess|profiles|program|progression|projection|promiscuous|promotion|pronouns|propelled|prophet|proposed|props|prosecutorial|prostitute|protectors|protests|prouder|provide|provisions|prowler|pry|psychiatrist|psychologically|psychotherapist|publically|publishing|puffs|pulmonary|pumpkin|punctuation|punitive|pupils|purchasing|purification|purr|push|putrid|pygmy|quadrant|qualifying|quark|queasy|questioned|quickie|quince|quite|quizzes|rabbits|racial|racks|radiator|radishes|ragging|raids|rained|raises|ralph|ramus|randy|ranks|rapid|rare|rat|ration|rattled|ravenous|rayed|reacquainted|reacts|real|realization|reamed|rearrange|reassign|rebelling|rebuilding|recap|receiving|rechecked|reckless|reclusive|recollection|reconciled|reconvene|recover|recrimination|rectory|reddish|redial|reduce|reed|reeled|referenced|refill|reflective|refreshed|refund|regaining|regenerated|region|regrets|regulate|rehashing|reimburse|reinstatement|rejections|relapsing|relatively|release|reliable|religious|reluctant|remake|remedied|reminder|remorse|renal|renew|renowned|reorganize|reparations|repent|replaced|replied|repository|repress|reproduction|republicans|requiem|reread|rescuer|resemble|reservation|residence|resignation|resolute|resourceful|respects|responses|restaurateur|restrain|restructuring|resurrection|retaliating|retinas|retreated|retrograde|rev|revenge|reversed|revised|revoking|revolves|rewriting|rhymed|rice|ricochet|rider|riff|righteousness|rigs|rings|ripe|rises|ritter|riverbank|roadhouse|roast|robbin|robs|rocky|rogue|rolls|romp|rookies|rooster|rose|rotary|rotten|roughriders|rousted|roving|royalties|rubes|rudimentary|ruin|rum|rumour|rune|runway|rusted|sabbath|sac|sacrificing|sadistic|safest|sailboats|sake|salary|saline|saltines|salvation|sanction|sandbar|sandwiches|sank|saran|sassy|satisfied|sauerkraut|saved|savoring|saxophone|scag|scalped|scams|scar|scariest|scattering|scented|schemes|schmoozing|school|sciences|scoliosis|scopes|scoring|scoundrel|scram|scraping|scrawny|screening|screwy|scrolls|scruples|sculpture|scuzzy|seaman|searching|seasons|secondary|secretly|securities|seduces|seeker|seeping|seized|selections|seller|semiautomatic|send|senora|sensible|sentence|sentry|sequel|serene|sermons|serves|sessions|settlement|seventies|sewage|sexism|shabby|shadowing|shah|shallow|shameless|shapely|sharking|sharpshooters|shaving|shedding|sheldrake|shelters|shh|shill|shingles|shipshape|shiver|shoddy|shoot|shoplifters|shortage|shortly|should|shoveled|shower|showstopper|shrieking|shrouds|shtick|shushing|shyness|sickens|sideburns|sidewalk|sifting|sigma|significant|sikes|sill|similarities|simpletons|simulations|sinful|singling|sins|siren|sisters|situated|sixty|skate|skeletal|skewed|skiff|skimp|skipping|skulk|slacker|slang|slashing|slavery|sleazeball|sleeping|sleeve|slicer|slighted|slings|slipping|slogans|slow|slugging|slurp|smallest|smashing|smelt|smith|smokes|smoothest|smuggle|snagged|snapper|snatched|sneaking|snide|snipers|snooping|snorted|snowbank|snowstorm|snuggling|soared|sobs|society|soda|softener|soiled|solely|solidify|solves|something|song|soon|sophistication|sores|sorts|sounded|source|souvenir|sox|spacing|spanking|sparklers|spasm|speak|specialise|specialty|speck|spectral|speechless|speedy|spend|spic|spiked|spinach|spiral|spiteful|spleen|splits|spoken|sponsorship|spoon|sporty|spotty|sprays|springing|sprite|spunk|squad|squares|squawk|squeezed|squirrels|stability|staff|staggering|stairs|stalemate|stallion|standard|standup|stare|starry|startling|stat|statesmen|statistical|statutes|steady|steamed|steer|stench|stepson|steroid|stickers|stifling|stimulated|stingy|stir|stockbrokers|stoic|stomped|stood|stopping|stork|stowaway|straightforward|stranded|strangler|strategies|streak|streetwalker|stressful|strictly|strings|strips|strolls|structured|strutting|student|studying|stump|stunt|stupor|stylish|subconsciously|subletting|submitting|subsidiary|substation|subtlety|subzero|succotash|suckers|sues|suffocating|suggestion|suitcase|sulking|summer|sumptuous|sunflower|sunrise|super|superiors|superstars|suppertime|supporter|suppression|surfboard|surgical|surprises|surrounding|survivor|suspending|sustenance|swam|swapped|swayed|sweats|sweeping|sweetly|swerving|swims|swiped|switches|swoops|syllable|symbols|synagogue|syndrome|syrup|table|tach|tacky|tadpole|tailing|takedown|talcum|talked|tally|tampons|tank|tantrum|tapioca|targets|tarts|tasting|tau|taxed|teach|teammate|teary|technically|teddy|teens|telekinetic|telescope|tells|tempest|tempting|tendency|tenner|tenth|terminate|terrace|terrify|terrorized|testified|tet|texts|thanksgiving|theaters|them|theoretical|thereby|thesaurus|thicker|thinkers|thirst|thong|thou|thrash|threats|thrilling|throttle|thru|thumbs|thy|ticker|tidal|ties|tighter|tiller|timely|tinfoil|tinsel|tipsy|tis|tits|tobacco|toenail|toiling|toll|tomcat|toner|tons|toons|toots|topping|torching|torpedoes|tortures|totaled|touchdowns|toughest|tournaments|town|toxin|traces|tractors|traditionally|trailer|trains|trample|transatlantic|transform|transit|transmissions|transportation|trapper|trauma|travelled|treacherous|treasury|treaty|tremors|trespassing|tribunal|tricks|trifling|trimester|triple|triumphed|troopers|trouble|trousers|true|trunk|trustworthy|tsk|tuck|tumbler|tung|turd|turncoat|turret|tutor|twas|twelfth|twin|twists|tycoon|typing|uglies|ultimatum|umpteenth|unappealing|unaware|unborn|unchanged|uncommon|uncontrollable|uncut|underdog|undergraduate|undermining|understanding|underway|undiscovered|undue|unencumbered|unfair|unfolding|unfreezing|unhappy|unidentified|uninspired|unique|universes|unless|unlocking|unmistakable|unpacked|unprecedented|unquote|unresolved|unsatisfying|unseen|unspoken|unsuspecting|unto|unusually|unwise|upbringing|upgrades|upload|uproar|upstage|upwards|urges|urologist|uses|utilities|vacated|vagabond|valet|valuables|vamps|vanity|variance|vary|vee|vegetative|velocity|venetian|ventilation|venue|verge|vermouth|vertical|vestibule|vets|vibrations|victimless|videotaped|vig|villa|vindictiveness|violates|violinist|virtual|visage|visitation|visualizing|vivacious|vocational|volatile|volumes|vortex|vow|vulnerable|wade|wagon|waiter|waiver|wall|wallpaper|wandered|war|warhead|warms|warrant|wartime|washout|wastes|water|waterworks|waving|wayward|weaknesses|wearing|weaver|wedge|weekend|weepy|weiner|welch|well|were|wets|whammo|wheat|when|whet|whimper|whiplash|whiskey|whistling|whiz|wholly|whore|widening|wieners|wigs|will|willpower|wind|windowsill|winged|winner|wiper|wiring|wished|with|withering|witness|woe|womanizer|wondering|wooden|wooing|words|workings|world|worried|worshiped|would|wraith|wreak|wrecks|wring|write|wrongful|wuthering|yam|yapping|yea|yeast|yep|yin|yoke|younger|youthful|yuppies|zee|zeta|zippy|zoning");
    for (String word: words.split("\\|")) {
      dictionary.add(word);
    }
  }

  public static void main(String[] args) {
    Server server = ServerBuilder.forPort(port)
        .addService(new SpellingServer()).build();
    try {
      server.start();
      System.out.println("Spelling server listening on port " + port);
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void check(Candidate request,
      io.grpc.stub.StreamObserver<Result> responseObserver) {
    String word = request.getWord();
    boolean found = dictionary.contains(word);
    System.out.println("Server on port " + port + ", " + word + ":"+ found);
    responseObserver.onNext(Result.newBuilder().setOk(found).build());
    responseObserver.onCompleted();
  }
};