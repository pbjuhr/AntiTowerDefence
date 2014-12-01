package antiTowerDefence;

public class LevelStats {

    public Level(Terrain[][], LevelStats)
 // 1. Sätter map och stats
 // 2. Initierar towers och units
 Terrain[][] map
 Tower[] towers
 Unit[] units
 LevelStats stats
 Position startPos
 addUnit(Unit unit):void
 setTowers(Tower[]):void
 updateMap():void 
 // Efter units och towers har gjort
 sina “actions”, så går den i genom towers och units och kollar om torn eller gubbar tillkommit.
 alterTerrain(Terrain t):void 
 // Lägger till exempelvis en Portal på en väg
 getUnits():Unit[]
 getTowers():Tower[]

    
}
