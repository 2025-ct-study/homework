from collections import defaultdict

def solution(nodes, edges):
    parent = {node : node for node in nodes} # Union-Find의 부모 노드
    edge_count = {node : 0 for node in nodes}

    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]
    
    def union(a, b):
        root_a = find(a)
        root_b = find(b)
        if root_a != root_b:
            parent[root_b] = root_a

    # Union-Find 수행 및 각 노드의 간선 개수 계산
    for a, b in edges:
        edge_count[a] += 1
        edge_count[b] += 1
        union(a, b)

    # 각 트리 그룹별 정보 저장
    tree_info = defaultdict(lambda: {'odd' : 0, 'even' : 0, 'reverse_odd' : 0, 'reverse_even' : 0})

    for node in nodes:
        root = find(node)
        count = edge_count[node]
        if node % 2 == 1 and count % 2 == 1:
            tree_info[root]['odd'] += 1
        elif node % 2 == 0 and count % 2 == 0:
            tree_info[root]['even'] += 1
        elif node % 2 == 1 and count % 2 == 0:
            tree_info[root]['reverse_odd'] += 1
        elif node % 2 == 0 and count % 2 == 1:
            tree_info[root]['reverse_even'] += 1

    tree_count = 0
    reverse_tree_count = 0

    for info in tree_info.values():
        if (info['odd'] == 1 and info['even'] == 0) or (info['odd'] == 0 and info['even'] == 1):
            tree_count += 1
        if (info['reverse_odd'] == 1 and info['reverse_even'] == 0) or (info['reverse_odd'] == 0 and info['reverse_even'] == 1):
            reverse_tree_count += 1

    return [tree_count, reverse_tree_count]