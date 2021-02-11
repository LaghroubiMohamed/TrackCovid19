#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
                                        // déclaration des fonction
struct annuaire* allocation(struct annuaire *T,int N);
void saisie(struct annuaire *T,int N);
void affich(struct annuaire *T,int N);
void tri(struct annuaire *T,int N);
void recherche(struct annuaire *T,int N,char ch[30]);
// déclaration de la sturucture
struct annuaire
{
 char nom[30];
 char prenom[30];

 int num_telephone;
 int jour;
 int mois;
 long annee;
};

                                                  // programme principal
main()
{
 struct annuaire *liste;
 int n;
 char ch[30];
 printf("Entrer un nombre des personnes:\n");
 scanf("%d",&n);
 liste=allocation(liste,n);
 getchar();
 printf("----------------- la liste des personnes:--------------\n");
 printf("\n");
 saisie(liste,n);
 printf("\n");
 printf("----------La liste des personnes avant le tri:----------\n");
 affich(liste,n);
 tri(liste,n);
 printf("\n");
 printf("-------------La liste des personnes apres le tri:----------\n");
 affich(liste,n);
 printf("\n");
 printf("----------Entrer le nom a rechercher:----------------\n");
 gets(ch);
 recherche(liste,n,ch);
getch();
}
                                                  // définition des fonction

struct annuaire* allocation(struct annuaire *T,int N)
{
 T=(struct annuaire*)malloc(N*sizeof(struct annuaire));
 if(T==0)
 printf("pas d'espace memoire:\n");
 return(T);
}


                                                 //function de saisie
void saisie(struct annuaire *T,int N)
{
 int i;
 for(i=0;i<N;i++)
 {
 printf("Entrer le nom de personne %d:\n",i+1);
 gets(T[i].nom);
 printf("Entrer le prenom de personne %d:\n",i+1);
 gets(T[i].prenom);
 printf("Entrer le jour de date naissance %d:\n",i+1);
         //jour
 scanf("%d",&T[i].jour);
 printf("Entrer le mois de date naissance  %d:\n",i+1);
                //mois
 scanf("%d",&T[i].mois);
 printf("Entrer l'annee de date naissance  %d:\n",i+1);
                // annee
 scanf("%ld",&T[i].annee);
 printf("Entrer le numumero de telephone en commence seulment par le (6)\n");
 scanf("%d",&T[i].num_telephone);
 getchar();
 printf("\n");
}
}
                                                      //function d'afficher
void affich(struct annuaire *T,int N)
{
 int i;
 printf(" Nom prenom numTel dateNaissance\n");
 for(i=0;i<N;i++)
 printf("%s %s +212%d %d/%d/%ld\n",T[i].nom,T[i].prenom,T[i].num_telephone,T[i].jour,T[i].mois,T[i].annee);
}
                                                    //function de trie
void tri(struct annuaire *T,int N)
{
 int i,j;
 struct annuaire temp;
 i=N-1;
 while(i>=0)
 {
 for(j=0;j<i;j++)
 {
 if(strcmp(T[j].nom,T[j+1].nom)>0)
 {
 temp=*(T+j);
 *(T+j)=*(T+j+1);
 *(T+j+1)=temp;
 }
 }
 i--;
 }
 }
                                                //function de recherche
void recherche(struct annuaire *T,int N,char ch[30])
{
int i;
i=0;
while( strcmp(ch,T[i].nom)!=0 && i<N)
i++;
if(i>=N)
printf("ce nom n\'existe pas dans la liste:\n");
else
printf(" le nom rechercher est %s %s, numero tel est +212%d date naissance est %d / %d / %ld \n",T[i].nom,T[i].prenom,T[i].num_telephone,T[i].jour,T[i].mois,T[i].annee);
}

