#include <stdio.h>
#include <stdlib.h>

typedef struct quickfind {
	int  capacity;
	int* id;
} quickfind;

quickfind* quickfind_create(unsigned int n) {
	quickfind* qf = malloc(sizeof(quickfind));

	if (qf == NULL) return NULL;

	qf->capacity = n;

	qf->id = malloc(n * sizeof(int));
	for (unsigned int i = 0; i < n; i++) {
		qf->id[i] = i;
	}

	return qf;
}

int quickfind_connected(quickfind* qf, unsigned int p, unsigned int q) {
	return qf->id[p] == qf->id[q];
}

void quickfind_union(quickfind* qf, unsigned int p, unsigned int q) {
	int pid = qf->id[p];
	int qid = qf->id[q];

	for (unsigned int i = 0; i < qf->capacity; i++)
		if (qf->id[i] == pid) qf->id[i] = qid;
}

void quickfind_free(quickfind* qf) {
	if (qf->id != NULL) free(qf->id);
	if (qf != NULL) free(qf);
}

int main(void) {
	int data[23] = {
		4, 3, 3, 8, 6, 5, 9,
		4, 2, 1, 8, 9, 5, 0,
		7, 2, 6, 1, 1, 0, 6, 7
	};

	quickfind* qf = quickfind_create(10);

	for (unsigned int i = 0; i < 22; i += 2) {
		if (!quickfind_connected(qf, data[i], data[i + 1])) {
			quickfind_union(qf, data[i], data[i + 1]);
			printf("%d %d\n", data[i] , data[i + 1]);
		}
	}

	for (unsigned int i = 0; i < qf->capacity; i++) {
		printf("%d ", qf->id[i]);
	}
	printf("\n");

	quickfind_free(qf);

	return 0;
}
