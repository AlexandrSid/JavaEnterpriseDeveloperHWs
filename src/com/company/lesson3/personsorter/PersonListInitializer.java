package com.company.lesson3.personsorter;


import java.util.Random;

public class PersonListInitializer {
    //Первое, что нашёл по запросам "список жеских\мужских имен через запятую"
    private static String listOfWomanNames = "Abigail, Ada, Addy, Adelaide, Adele, Agatha, Agnes, Alaina, Alanna, Alberta, Alice, Aliso, Alvina, Amanda, Ambe, Amelia, Amy, Andrea, Ange, Angela, Anna, Annabe, Arda, Audrey, Augusta, Autum, Avi, Barbara, Beatrice, Belinda, Bella, Berenice, Bertha, Brenda, Bridget, Bronwen, Cadence, Carmelita, Caroline, Carolyn, Carolynn, Cassandra, Cecilia, Cecily, Celia, Charis, Charisse, Charity, Charlotte, Charmaine, Cheryl, Chloe, Christabel, Claribel, Clarissa, Clementine, Cleo, Cordelia, Cristalyn, Crystal, Cynthia, Daisy, Daphne, Darryl, Davina, Dawn, Deanna, Deanne, Deborah, Dede, Delia, Denise, Destiny, Diana, Dolores, Dora, Doreen, Dorothy, Drusilla, Dulcie, Edith, Edna, Edwina, Effie, Eileen, Eleanor, Elektra, Elizabeth, Ella, Ellen, Emma, Enid, Estelle, Ethel, Eudora, Eunice, Eva, Faith, Felicity, Fiona, Flora, Galenka, Gaynor, Gemma, Genevieve, Georgiana, Gertie, Gertrude, Gia, Glenda, Gwen, Gwenda, Gwendolen, Gwendoline, Gwendolyn, Hannah, Harriet, Helen, Henrietta, Hero, Hester, Honor, Hope, Ida, Imelda, Imogen, InnogeIona, Irene, IriIsla, Ivy, Jacqueline, Jacqui, Jaime, Jane, Jemima, Jenna, Jennifer, Jessica, Jessie, Joanna, Joanne, Joelle, Joey, Josephine, Judith, Julianne, June, Karen, Kathleen, Kaylee, Kierra, Lara, Laura, Lauren, Leah, Lettice, Liana, Lilla, Lillia, Lois, Lorelei, Loretta, Lorna, Lorraine, Louisa, Lucinda, Lucy, Lynnette, Mabel, Madge, Maggie, Marcia, Marcie, Margaret, Marian, Marianne, Marilyn, Marissa, Marjorie, Marsha, Matilda, Maud, Maude, Mavis, May, Medea, Mehitable, Melanie, Melissa, Michele, Millicent, Minna, Moira, Myra, Myrna, Myrtle, Nadine, Naila";
    private static String listOfManNames = "Abraham, Addison, Adrian, Albert, Alec, Alfred, Alvin, Andrew, Andy, Archibald, Archie, Arlo, Arthur, Arthur, Austen, Barnabe, Bartholomew, Bertram, Bramwell, Byam, Cardew, Chad, Chance, Colin, Coloman, Curtis, Cuthbert, Daniel, Darryl, David, Dickon, Donald, Dougie, Douglas, Earl, Ebenezer, Edgar, Edmund, Edward, Edwin, Elliot, Emil, Floyd, Franklin, Frederick, Gabriel, Galton, Gareth, George, Gerard, Gilbert, Gorden, Gordon, Graham, Grant, Henry, Hervey, Hudson, Hugh, Ian, Jack, Jaime, James, Jason, Jeffrey, Joey, John, Jolyon, Jonas, Joseph, Joshua, Julian, Justin, Kurt, Lanny, Larry, Laurence, Lawton, Lester, Malcolm, Marcus, Mark, Marshall, Martin, Marvin, Matt, Maximilian, Michael, Miles, Murray, Myron, Nate, Nathan, Neil, Nicholas, Nicolas, Norman, Oliver, Oscar, Osric, Owen, Patrick, Paul, Peleg, Philip, Phillipps, Raymond, Reginald, Rhys, Richard, Robert, Roderick, Rodger, Roger, Ronald, Rowland, Rufus, Russell, Sebastian, Shahaf, Simon, Stephen, Swaine, Thomas, Tobias, Travis, Victor, Vincent, Vincent, Vivian, Wayne, Wilfred, William, Winston, Zadoc";
    private static String[] womanNames = listOfWomanNames.split(", ");
    private static String[] manNames = listOfManNames.split(", ");

    public static Person[] getInstance(int size) {
        Random random = new Random();
        Person[] result = new Person[size];
        for (int j = 0; j<size; j++) {
            int i = random.nextInt(2);//0 or 1 values
            Sex sex = (i==1)?Sex.MAN:Sex.WOMAN;
            String[] nameSpace = (sex==Sex.MAN)?manNames:womanNames;
            String name = nameSpace[random.nextInt(nameSpace.length)];
            int age = random.nextInt(101);//(возраст, целое число 0-100)
            result[j] = new Person(age, sex, name);
        }
        return result;
    }
}
