/*

here are changes applied to each packages.

cache
-added CacheByValue to enable cache that depend on input value.

collection
-added capability to cache calculated list elements by CacheList
-added capability for calculating homogeneous permutation. for that purpose, 
 classes below are added.
    Divide, HomogeneousPermutation
-added capability to handle recursively constructed P as a list of Object.
-Scale is now RandomAccess (as it should be). added ShiftedScale to handle 
 where there is a need to handle a row of numbers which can start from negative 
 number.
-added FoldList to fold list into fixed length of list (except last one).
-TList was changed in several parts
    -even from stream, you can come back to TList by using collector provided by
     toTList().
    -thanks to CacheList, now cache() will take cache of the list.
    -thanks to ShiftedScale, now range() can have negative values.
    -now range can take Range<Integer> as parameter.
    -rangeSym is a symmetric variation of range. this is convenient when you are
     calculating something symmetric like a point in space.
    -now TList does calculation sumI(), sumL() and sumD() (each one is variation 
     for int, long and double) and averageI(), averageL() and averageD().
    -thanks to FoldList, now TList can fold() itself.
    -now chunk returns the remain of chunking.
    -now TList can nCopies(). you don't have to go back to Collections.
    -now TList can count changes happen in the list.
    -now subList() can accept Range<Integer> as parameter
    -now set() accepts any Collection
    -transpose and combination now have TList<TList variation, transposeT and
     combinationT
    -several debugging and testing facilities below
         fixDebug() will print out excerpts of the list
         miscellaneous variations for toString()
              toWrappedString(), toFlatString(), toDelimitedString()
         now TList can be used as a data provider for TestNG

function
-added CastPredicate to find a certain object even from a heterogeneous list.

iterator
-fixed a bug in ListIteratorIterator, in which when the instance is previous()-ed 
 right after created, the reference was broken.
-removed old and rarely used package ViewList, but View class was remained to be 
 used by RingBufferListIterator. so now it is renamed as RingBufferView and moved 
 to iterator package.
-now TIterator can heap() without start value.

optimize
-this is a brand new package is considered to provide optimization facilities to 
 TERSE3. as of now, it contains search algorithm for path in graph based on AStar
 algorithm.
 AStar and AStarNode is the most basic combination.

orderedSet
-Range is too abstract to contain calculations like width or shift even though
 they are widely used. RangeUtil is a help for that situation

shape
-added capability to handle discreet value pair by TPoint2i. and at the same time,
 added Rect2i to handle Rectangle described by TPoint2i.
-added Triangle and its subclasses to do calculations around triangles.
-added Point wrappers these functions
    setX, setY, setZ (each S and R), interpolate, flip, average
-added Vector wrappers these functions
    setX, setY, setZ, interpolate, rotCcw, rotCw, flip, average, hypotenuseOf, theOtherSideOf,
    quadrant, relative, disrelative

shapeCollection
-newly added package to represent collection aspect of shapes. 
    Grid is used to represent 2-dimentional set.
    EdgeSet2i, PolygonSet2i, and RectSet2i are calculation based set which will 
    tell whether a certain point is included or not.

string
-fixed bug in csv() which failed when the list is empty
-Strings has several new functionalities like:
    concat(): to concatenate Strings in list.
    asCharList(): to handle String as a list of Character.
    wrap(): to wrap a String at a certain length.

system
-newly added package to collect useful things for running a system. as of now, it 
 only contains ExecLevel which represents running level of the system like 
 PRODUCTION or DEBUG

test
-added Assert class to collect assertion for tests. as of now it only contains
 assertIdentical() to check all the elements of iterables are identical.
-added Namer to manage the name of object attached from outside of the object.
-JREVersion is a convenient way to find out the actual version of running JRE.
 it's mainly a memo for certain usage of System.getProperty().
-
*/